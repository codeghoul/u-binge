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
    private Long customerId;

    private Long deliveryGuyId;

    private Long restaurantId;

    private LocalDateTime timestamp;

    private PaymentMode paymentMode;

    private OrderStatus orderStatus;

    private Double totalPrice;

    private Set<Long> orderFoodItems = new HashSet<>();
}
