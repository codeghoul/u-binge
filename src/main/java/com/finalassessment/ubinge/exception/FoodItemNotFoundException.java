package com.finalassessment.ubinge.exception;

public class FoodItemNotFoundException extends IllegalArgumentException {
    public FoodItemNotFoundException(Long foodItemId) {
        super("No Food Item with Id: " + foodItemId);
    }
}
