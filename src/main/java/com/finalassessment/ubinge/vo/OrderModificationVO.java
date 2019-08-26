package com.finalassessment.ubinge.vo;

import com.finalassessment.ubinge.model.OrderStatus;
import com.finalassessment.ubinge.model.PaymentMode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderModificationVO {
    OrderStatus orderStatus;
    PaymentMode paymentMode;
}
