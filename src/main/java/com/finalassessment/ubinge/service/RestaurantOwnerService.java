package com.finalassessment.ubinge.service;

import com.finalassessment.ubinge.model.Restaurant;
import com.finalassessment.ubinge.model.RestaurantOwner;
import com.finalassessment.ubinge.vo.GeneralDetailVO;

import java.util.List;

public interface RestaurantOwnerService extends CrudService<RestaurantOwner, Long> {
    RestaurantOwner saveRestaurants(Long restaurantOwnerId, List<Restaurant> restaurants);

    RestaurantOwner deleteRestaurants(Long restaurantOwnerId, List<Long> restaurantIds);

    RestaurantOwner update(GeneralDetailVO generalDetailVO, Long restaurantOwnerId);

    Restaurant updateRestaurantDetails(GeneralDetailVO generalDetailVO, Long restaurantOwnerId, Long restaurantId);

    List<Restaurant> findAllRestaurants(Long restaurantOwnerId);
}
