package com.finalassessment.ubinge.service;

import com.finalassessment.ubinge.dto.RestaurantDTO;
import com.finalassessment.ubinge.dto.RestaurantOwnerDTO;

import java.util.List;

public interface RestaurantOwnerService extends CrudService<RestaurantOwnerDTO, Long> {
    RestaurantOwnerDTO saveRestaurants(Long restaurantOwnerId, List<RestaurantDTO> restaurantDTOs);

    RestaurantOwnerDTO deleteRestaurants(Long restaurantOwnerId, List<Long> restaurantIds);

    RestaurantOwnerDTO update(RestaurantOwnerDTO restaurantOwnerDTO, Long restaurantOwnerId);

    RestaurantDTO updateRestaurantDetails(RestaurantDTO restaurantDTO, Long restaurantOwnerId, Long restaurantId);

    List<RestaurantDTO> findAllRestaurants(Long restaurantOwnerId);
}
