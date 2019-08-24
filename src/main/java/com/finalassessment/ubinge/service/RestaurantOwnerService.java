package com.finalassessment.ubinge.service;

import com.finalassessment.ubinge.model.Restaurant;
import com.finalassessment.ubinge.model.RestaurantOwner;

import java.util.List;

public interface RestaurantOwnerService extends CrudService<RestaurantOwner, Long> {
    RestaurantOwner saveRestaurants(Long restaurantOwnerId, List<Restaurant> restaurants);

    RestaurantOwner deleteRestaurants(Long restaurantOwnerId, List<Long> restaurantIds);
}
