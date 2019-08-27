package com.finalassessment.ubinge.service;

import com.finalassessment.ubinge.dto.FoodItemDTO;
import com.finalassessment.ubinge.dto.OrderDTO;
import com.finalassessment.ubinge.dto.OrderModificationDTO;
import com.finalassessment.ubinge.dto.RestaurantDTO;

import java.util.List;

public interface RestaurantService extends CrudService<RestaurantDTO, Long> {
    RestaurantDTO addFoodItems(Long restaurantId, List<FoodItemDTO> foodItemDTOs);

    RestaurantDTO removeFoodItems(Long restaurantId, List<Long> foodItemIds);

    List<OrderDTO> getRestaurantOrders(Long restaurantId);

    OrderDTO getRestaurantOrderById(Long restaurantId, Long orderId);

    OrderDTO modifyOrder(Long restaurantId, Long orderId, OrderModificationDTO modification);

    List<FoodItemDTO> getRestaurantFoodItems(Long restaurantId);
}
