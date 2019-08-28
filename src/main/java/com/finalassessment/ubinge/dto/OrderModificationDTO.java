package com.finalassessment.ubinge.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.finalassessment.ubinge.constants.OrderStatus;
import com.finalassessment.ubinge.constants.PaymentMode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderModificationDTO {
    OrderStatus orderStatus;
    PaymentMode paymentMode;
}
