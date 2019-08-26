package com.finalassessment.ubinge.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class RestaurantDTO extends GeneralDetailsDTO {
    private RestaurantOwnerDTO restaurantOwner;

    private Set<FoodItemDTO> foodItems = new HashSet<>();

    private Set<OrderDTO> orders = new HashSet<>();
}
