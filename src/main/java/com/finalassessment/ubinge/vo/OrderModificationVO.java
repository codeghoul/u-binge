package com.finalassessment.ubinge.vo;

import com.finalassessment.ubinge.constants.OrderStatus;
import com.finalassessment.ubinge.constants.PaymentMode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderModificationVO {
    OrderStatus orderStatus;
    PaymentMode paymentMode;
}
