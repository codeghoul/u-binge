package com.finalassessment.ubinge.service.impl;

import com.finalassessment.ubinge.exception.CustomerNotFoundException;
import com.finalassessment.ubinge.exception.FoodItemNotFoundException;
import com.finalassessment.ubinge.exception.OrderNotFoundException;
import com.finalassessment.ubinge.exception.RestaurantNotFoundException;
import com.finalassessment.ubinge.model.*;
import com.finalassessment.ubinge.repository.*;
import com.finalassessment.ubinge.service.OrderService;
import com.finalassessment.ubinge.vo.OrderFoodItemVo;
import com.finalassessment.ubinge.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private RestaurantRepository restaurantRepository;
    private FoodItemRepository foodItemRepository;
    private OrderFoodItemRepository orderFoodItemRepository;
    private DeliveryGuyRepository deliveryGuyRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository,
                            RestaurantRepository restaurantRepository, FoodItemRepository foodItemRepository,
                            OrderFoodItemRepository orderFoodItemRepository, DeliveryGuyRepository deliveryGuyRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
        this.foodItemRepository = foodItemRepository;
        this.orderFoodItemRepository = orderFoodItemRepository;
        this.deliveryGuyRepository = deliveryGuyRepository;
    }

    @Override
    public List<Order> findAll() {
        log.debug("Getting Orders from Service.");
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long orderId) {
        log.debug("Getting Order by Id from Service.");
        return orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    @Override
    public Order save(Order newOrder) {
        log.debug("Saving order from Service.");
        return orderRepository.save(newOrder);
    }

    @Override
    public Order update(Order order, Long orderId) {
        log.debug("Updating Order from Service.");
        return null;
    }

    @Override
    public void delete(Order order) {
        log.debug("Deleting order from Service.");
        orderRepository.delete(order);
    }

    @Override
    public void deleteById(Long orderId) {
        log.debug("Deleting order by id from Service.");
        orderRepository.deleteById(orderId);
    }

    @Override
    public Order createOrder(OrderVO orderVO) {
        Long customerId = orderVO.getCustomerId();
        Long restaurantId = orderVO.getRestaurantId();
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
        Order order = new Order();
        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setDeliveryGuy(null);
        order.setOrderStatus(OrderStatus.APPROVED);
        order.setPaymentMode(orderVO.getPaymentMode());
        order.setTimestamp(LocalDateTime.now());
        order.setTotalPrice(orderVO.getTotalPrice());

        List<OrderFoodItem> orderFoodItems = orderVO.getOrderFoodItemVos().stream().map(orderFoodItemVo -> orderFoodVoConverter(orderFoodItemVo)).collect(Collectors.toList());
        orderFoodItems.forEach(orderFoodItem -> orderFoodItem.setOrder(order));
        orderFoodItemRepository.saveAll(orderFoodItems);

        return orderRepository.save(order);
    }

    private OrderFoodItem orderFoodVoConverter(OrderFoodItemVo orderFoodItemVo) {
        OrderFoodItem orderFoodItem = new OrderFoodItem();
        Long foodItemId = orderFoodItemVo.getFoodItemId();
        FoodItem foodItem = foodItemRepository.findById(foodItemId).orElseThrow(() -> new FoodItemNotFoundException(foodItemId));
        orderFoodItem.setFoodItem(foodItem);
        orderFoodItem.setQuantity(orderFoodItemVo.getQuantity());
        orderFoodItem.setTotalPrice(orderFoodItemVo.getTotalPrice());
        return orderFoodItem;
    }
}