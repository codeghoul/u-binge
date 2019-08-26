package com.finalassessment.ubinge.service.impl;

import com.finalassessment.ubinge.constants.OrderStatus;
import com.finalassessment.ubinge.constants.PaymentMode;
import com.finalassessment.ubinge.exception.OrderNotFoundException;
import com.finalassessment.ubinge.exception.OrderStatusException;
import com.finalassessment.ubinge.exception.PaymentModeException;
import com.finalassessment.ubinge.exception.RestaurantNotFoundException;
import com.finalassessment.ubinge.model.FoodItem;
import com.finalassessment.ubinge.model.Order;
import com.finalassessment.ubinge.model.Restaurant;
import com.finalassessment.ubinge.repository.FoodItemRepository;
import com.finalassessment.ubinge.repository.OrderRepository;
import com.finalassessment.ubinge.repository.RestaurantRepository;
import com.finalassessment.ubinge.service.RestaurantService;
import com.finalassessment.ubinge.vo.OrderModificationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RestaurantServiceImpl implements RestaurantService {
    private RestaurantRepository restaurantRepository;
    private FoodItemRepository foodItemRepository;
    private OrderRepository orderRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, FoodItemRepository foodItemRepository, OrderRepository orderRepository) {
        this.restaurantRepository = restaurantRepository;
        this.foodItemRepository = foodItemRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Restaurant> findAll() {
        log.debug("Getting Restaurants from Service.");
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant findById(Long restaurantId) {
        log.debug("Getting Restaurant By Id from Service.");
        return restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
    }

    @Override
    public Restaurant save(Restaurant newRestaurant) {
        log.debug("Saving Restaurant from Service.");
        return restaurantRepository.save(newRestaurant);
    }

    public Restaurant update(Restaurant restaurant, Long restaurantId) {
        log.debug("Updating Restaurant from Service.");
        return null;
    }

    @Override
    public void delete(Restaurant restaurant) {
        log.debug("Deleting Restaurant from Service.");
        restaurantRepository.delete(restaurant);
    }

    @Override
    public void deleteById(Long restaurantId) {
        log.debug("Deleting Restaurant By Id from Service.");
        restaurantRepository.deleteById(restaurantId);
    }

    @Override
    public Restaurant addFoodItems(Long restaurantId, @org.jetbrains.annotations.NotNull List<FoodItem> foodItems) {
        log.debug("Adding Delicious Food Items.");
        Restaurant restaurant = findById(restaurantId);
        log.debug("Adding Food Items to Restaurant " + restaurant.getName() + " from Service. ");
        foodItems.stream().forEach(foodItem -> {
            foodItem.setRestaurant(restaurant);
            foodItemRepository.save(foodItem);
        });
        return restaurant;
    }

    @Override
    public Restaurant removeFoodItems(Long restaurantId, List<Long> foodItemIds) {
        Restaurant restaurant = findById(restaurantId);
        log.debug("Removing Food Items from Restaurant" + restaurant.getName() + " from Service. ");
        List<FoodItem> foodItems = foodItemRepository.findAllById(foodItemIds);
        foodItemRepository.deleteAll(foodItems);
        return restaurant;
    }

    @Override
    public List<Order> getRestaurantOrders(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
        return restaurant.getOrders().stream().collect(Collectors.toList());
    }

    @Override
    public Order getRestaurantOrderById(Long restaurantId, Long orderId) {
        log.debug("Getting Customer order by id.");
        Restaurant restaurant = findById(restaurantId);
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        if (!order.getRestaurant().equals(restaurant)) {
            throw new OrderNotFoundException(orderId);
        }
        return order;
    }

    @Override
    public Order modifyOrder(Long restaurantId, Long orderId, OrderModificationVO modification) {
        Order order = getRestaurantOrderById(restaurantId, orderId);

        OrderStatus orderStatus = modification.getOrderStatus();
        PaymentMode paymentMode = modification.getPaymentMode();

        if (paymentMode != null) {
            throw new PaymentModeException("Restaurant cannot change Payment Mode.");
        }

        if (order.getOrderStatus().getDescription().equals("approved")) {
            order.setOrderStatus(orderStatus);
        } else {
            throw new OrderStatusException("Restaurant cannot change status from " + order.getOrderStatus() + " to " + modification.getOrderStatus());
        }
        return order;
    }
}