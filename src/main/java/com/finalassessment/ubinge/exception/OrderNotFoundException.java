package com.finalassessment.ubinge.exception;

public class OrderNotFoundException extends NotFoundException {
    public OrderNotFoundException(Long orderId) {
        super("No Order found with Id: " + orderId);
    }
}
