package com.finalassessment.ubinge.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Order extends BaseEntity {
    private Customer customer;
    private DeliveryGuy deliveryGuy;
    private Restaurant restaurant;
    private LocalDateTime timestamp;
    private PaymentMode paymentMode;
    private OrderStatus orderStatus;
    private Double totalPrice;
    private Set<OrderFoodItem> orderFoodItems = new HashSet<>();
}
