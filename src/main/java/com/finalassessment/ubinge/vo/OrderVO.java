package com.finalassessment.ubinge.vo;

import com.finalassessment.ubinge.model.PaymentMode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderVO {
    private Long customerId;
    private Long restaurantId;
    private List<OrderFoodItemVo> orderFoodItemVos;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    private LocalDateTime timestamp;
    private Double totalPrice;
}
