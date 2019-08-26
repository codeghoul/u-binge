package com.finalassessment.ubinge.dto;

import com.finalassessment.ubinge.constants.OrderStatus;
import com.finalassessment.ubinge.constants.PaymentMode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class OrderDTO extends BaseEntityDTO {
    private CustomerDTO customer;

    private DeliveryGuyDTO deliveryGuy;

    private RestaurantDTO restaurant;

    private LocalDateTime timestamp;

    private PaymentMode paymentMode;

    private OrderStatus orderStatus;

    private Double totalPrice;

    private Set<OrderFoodItemDTO> orderFoodItems = new HashSet<>();
}
