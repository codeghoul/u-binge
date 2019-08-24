package com.finalassessment.ubinge.repository;

import com.finalassessment.ubinge.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
