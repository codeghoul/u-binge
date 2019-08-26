package com.finalassessment.ubinge.exception;

public class OrderFoodItemNotFoundException extends IllegalArgumentException {
    public OrderFoodItemNotFoundException(Long orderFoodItemId) {
        super("No Order Food Item with Id: " + orderFoodItemId);
    }
}
