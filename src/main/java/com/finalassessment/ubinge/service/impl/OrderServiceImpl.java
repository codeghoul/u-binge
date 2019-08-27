package com.finalassessment.ubinge.service.impl;

import com.finalassessment.ubinge.constants.OrderStatus;
import com.finalassessment.ubinge.exception.*;
import com.finalassessment.ubinge.model.*;
import com.finalassessment.ubinge.repository.*;
import com.finalassessment.ubinge.service.OrderService;
import com.finalassessment.ubinge.vo.OrderFoodItemVO;
import com.finalassessment.ubinge.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
        //TODO: break create order into multi small modules. If possible move error throwing in controller.
        log.debug("Create new order.");
        Order order = toOrder(orderVO);

        List<OrderFoodItem> orderFoodItems = orderVO.getOrderFoodItemVOs().stream()
                .map(orderFoodItemVO -> toOrderFoodItem(orderFoodItemVO)).collect(Collectors.toList());

        Double totalPrice = 0.0;

        for (OrderFoodItem orderFoodItem : orderFoodItems) {
            totalPrice += orderFoodItem.getTotalPrice();
        }

        if (BigDecimal.valueOf(totalPrice).compareTo(BigDecimal.valueOf(orderVO.getTotalPrice())) != 0) {
            throw new PriceMismatchException("Total Price for this order should be " + totalPrice + " but found " + orderVO.getTotalPrice());
        }

        order.setTotalPrice(totalPrice);
        orderRepository.save(order);
        orderFoodItems.forEach(orderFoodItem -> orderFoodItem.setOrder(order));


        orderFoodItemRepository.saveAll(orderFoodItems);
        return order;
    }

    private Order toOrder(OrderVO orderVO) {
        Long customerId = orderVO.getCustomerId();
        Long restaurantId = orderVO.getRestaurantId();

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));

        Order order = new Order();
        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setDeliveryGuy(getDeliveryGuy());
        order.setOrderStatus(OrderStatus.APPROVED);
        order.setPaymentMode(orderVO.getPaymentMode());
        order.setTimestamp(LocalDateTime.now());

        return order;
    }

    private OrderFoodItem toOrderFoodItem(OrderFoodItemVO orderFoodItemVo) {
        log.debug("Converting orderFoodItemVo into orderFoodItem");
        OrderFoodItem orderFoodItem = new OrderFoodItem();
        Long foodItemId = orderFoodItemVo.getFoodItemId();
        FoodItem foodItem = foodItemRepository.findById(foodItemId).orElseThrow(() -> new FoodItemNotFoundException(foodItemId));
        orderFoodItem.setFoodItem(foodItem);
        orderFoodItem.setQuantity(orderFoodItemVo.getQuantity());
        Double totalPrice = orderFoodItemVo.getQuantity() * foodItem.getPrice();
        if (BigDecimal.valueOf(totalPrice).compareTo(BigDecimal.valueOf(orderFoodItemVo.getTotalPrice())) != 0) {
            throw new PriceMismatchException("Total Price for " + foodItem.getName() + " should be " + totalPrice + " but found " + orderFoodItemVo.getTotalPrice());
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