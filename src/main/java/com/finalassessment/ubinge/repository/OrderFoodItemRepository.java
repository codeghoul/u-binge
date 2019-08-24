package com.finalassessment.ubinge.repository;

import com.finalassessment.ubinge.model.OrderFoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFoodItemRepository extends JpaRepository<OrderFoodItem, Long> {
}
