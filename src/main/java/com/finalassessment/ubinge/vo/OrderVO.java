package com.finalassessment.ubinge.vo;

import com.finalassessment.ubinge.model.PaymentMode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderVO {
    private Long customerId;
    private Long restaurantId;
    private List<OrderFoodItemVO> orderFoodItemVOS;

    private PaymentMode paymentMode;

    private LocalDateTime timestamp;
    private Double totalPrice;
}
