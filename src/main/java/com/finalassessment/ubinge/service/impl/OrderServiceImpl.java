package com.finalassessment.ubinge.service.impl;

import com.finalassessment.ubinge.constants.OrderStatus;
import com.finalassessment.ubinge.dto.OrderDTO;
import com.finalassessment.ubinge.dto.OrderFoodItemDTO;
import com.finalassessment.ubinge.exception.CustomerNotFoundException;
import com.finalassessment.ubinge.exception.FoodItemNotFoundException;
import com.finalassessment.ubinge.exception.PriceMismatchException;
import com.finalassessment.ubinge.exception.RestaurantNotFoundException;
import com.finalassessment.ubinge.model.*;
import com.finalassessment.ubinge.repository.*;
import com.finalassessment.ubinge.service.OrderService;
import com.finalassessment.ubinge.utility.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
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
    public List<OrderDTO> findAll() {
        return null;
    }

    @Override
    public OrderDTO findById(Long orderId) {
        return null;
    }

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
//        log.debug("Saving order from Service.");
//        return orderRepository.save(newOrder);
        return null;
    }

//    public Order update(Order order, Long orderId) {
//        log.debug("Updating Order from Service.");
//        return null;
//    }

    @Override
    public void deleteById(Long orderId) {
//        log.debug("Deleting order by id from Service.");
//        orderRepository.deleteById(orderId);
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        //TODO: break create order into multi small modules. If possible move error throwing in controller.
        log.debug("Create new order.");

        Long customerId = orderDTO.getCustomerId();
        Long restaurantId = orderDTO.getRestaurantId();

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));

        Order order = MapperUtil.toOrder(orderDTO);

        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setDeliveryGuy(getDeliveryGuy());

        List<OrderFoodItem> orderFoodItems = orderDTO.getOrderFoodItemDTOs().stream()
                .map(orderFoodItemDTO -> toOrderFoodItem(orderFoodItemDTO)).collect(Collectors.toList());

        Double totalPrice = 0.0;

        for (OrderFoodItem orderFoodItem : orderFoodItems) {
            totalPrice += orderFoodItem.getTotalPrice();
        }

        if (BigDecimal.valueOf(totalPrice).compareTo(BigDecimal.valueOf(orderDTO.getTotalPrice())) != 0) {
            throw new PriceMismatchException("Total Price for this order should be " + totalPrice + " but found " + orderDTO.getTotalPrice());
        }

        order.setTotalPrice(totalPrice);
        order.setOrderStatus(OrderStatus.APPROVED);
        orderRepository.save(order);
        orderFoodItems.forEach(orderFoodItem -> orderFoodItem.setOrder(order));
        orderFoodItemRepository.saveAll(orderFoodItems);
        return MapperUtil.toOrderDTO(order);
    }

    private OrderFoodItem toOrderFoodItem(OrderFoodItemDTO orderFoodItemDTO) {
        log.debug("Converting orderFoodItemVo into orderFoodItem");
        OrderFoodItem orderFoodItem = new OrderFoodItem();
        Long foodItemId = orderFoodItemDTO.getFoodItemId();
        FoodItem foodItem = foodItemRepository.findById(foodItemId).orElseThrow(() -> new FoodItemNotFoundException(foodItemId));
        orderFoodItem.setFoodItem(foodItem);
        orderFoodItem.setQuantity(orderFoodItemDTO.getQuantity());
        Double totalPrice = orderFoodItemDTO.getQuantity() * foodItem.getPrice();
        if (BigDecimal.valueOf(totalPrice).compareTo(BigDecimal.valueOf(orderFoodItemDTO.getTotalPrice())) != 0) {
            throw new PriceMismatchException("Total Price for " + foodItem.getName() + " should be " + totalPrice + " but found " + orderFoodItemDTO.getTotalPrice());
        }
        orderFoodItem.setTotalPrice(totalPrice);
        return orderFoodItem;
    }

    private DeliveryGuy getDeliveryGuy() {
        // TODO implement this in some non random way.
        log.debug("Getting a delivery guy.");
        List<DeliveryGuy> deliveryGuys = deliveryGuyRepository.findAll();
        Random random = new Random();
        return deliveryGuys.get(random.nextInt(deliveryGuys.size()));
    }
}