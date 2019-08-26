package com.finalassessment.ubinge.exception;

public class RestaurantOwnerNotFoundException extends IllegalArgumentException {
    public RestaurantOwnerNotFoundException(Long restaurantOwnerId) {
        super("No restaurant Owner Found with Id: " + restaurantOwnerId);
    }
}
