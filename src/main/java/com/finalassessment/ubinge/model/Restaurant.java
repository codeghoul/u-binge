package com.finalassessment.ubinge.model;

import java.util.Set;

public class Restaurant extends GeneralDetails {
    private RestaurantOwner restaurantOwner;
    private Set<FoodItem> foodItems;
    private Set<Order> orders;
}
