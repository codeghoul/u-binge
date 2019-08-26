package com.finalassessment.ubinge.exception;

public class OrderNotFoundException extends IllegalArgumentException {
    public OrderNotFoundException(Long orderId) {
        super("No Order found with Id: " + orderId);
    }
}
