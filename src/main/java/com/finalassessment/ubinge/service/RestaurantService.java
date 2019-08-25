package com.finalassessment.ubinge.service;

import com.finalassessment.ubinge.model.FoodItem;
import com.finalassessment.ubinge.model.Order;
import com.finalassessment.ubinge.model.Restaurant;

import java.util.List;

public interface RestaurantService extends CrudService<Restaurant, Long> {
    Restaurant addFoodItems(Long restaurantId, List<FoodItem> foodItems);

    Restaurant removeFoodItems(Long restaurantId, List<Long> foodItemIds);

    List<Order> getRestaurantOrders(Long restaurantId);
}
