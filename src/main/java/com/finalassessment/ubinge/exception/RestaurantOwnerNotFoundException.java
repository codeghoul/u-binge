package com.finalassessment.ubinge.exception;

public class RestaurantOwnerNotFoundException extends NotFoundException{
    public RestaurantOwnerNotFoundException(Long restaurantOwnerId) {
        super("No restaurant Owner Found with Id: " + restaurantOwnerId);
    }
}
