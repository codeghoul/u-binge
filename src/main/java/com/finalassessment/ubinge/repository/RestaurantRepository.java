package com.finalassessment.ubinge.repository;

import com.finalassessment.ubinge.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
