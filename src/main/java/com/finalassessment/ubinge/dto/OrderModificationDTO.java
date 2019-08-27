package com.finalassessment.ubinge.dto;

import com.finalassessment.ubinge.constants.OrderStatus;
import com.finalassessment.ubinge.constants.PaymentMode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderModificationDTO {
    OrderStatus orderStatus;
    PaymentMode paymentMode;
}
