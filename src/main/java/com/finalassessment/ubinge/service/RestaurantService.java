package com.finalassessment.ubinge.service;

import com.finalassessment.ubinge.dto.FoodItemDTO;
import com.finalassessment.ubinge.dto.OrderDTO;
import com.finalassessment.ubinge.dto.RestaurantDTO;
import com.finalassessment.ubinge.vo.OrderModificationVO;

import java.util.List;

public interface RestaurantService extends CrudService<RestaurantDTO, Long> {
    RestaurantDTO addFoodItems(Long restaurantId, List<FoodItemDTO> foodItemDTOs);

    RestaurantDTO removeFoodItems(Long restaurantId, List<Long> foodItemIds);

    List<OrderDTO> getRestaurantOrders(Long restaurantId);

    OrderDTO getRestaurantOrderById(Long restaurantId, Long orderId);

    OrderDTO modifyOrder(Long restaurantId, Long orderId, OrderModificationVO modification);

    List<FoodItemDTO> getRestaurantFoodItems(Long restaurantId);
}
