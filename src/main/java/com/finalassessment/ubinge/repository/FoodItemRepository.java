package com.finalassessment.ubinge.repository;

import com.finalassessment.ubinge.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
}
