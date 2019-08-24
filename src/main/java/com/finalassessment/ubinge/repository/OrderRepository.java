package com.finalassessment.ubinge.repository;

import com.finalassessment.ubinge.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
