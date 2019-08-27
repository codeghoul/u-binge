package com.finalassessment.ubinge.mapper;

import com.finalassessment.ubinge.model.Customer;
import com.finalassessment.ubinge.model.Order;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;

import javax.persistence.EntityManager;

public class CustomerContext {
    private final EntityManager entityManager;
    private Customer customer;

    public CustomerContext(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @BeforeMapping
    public void setEntity(@MappingTarget Customer customer) {
        this.customer = customer;
    }

    @AfterMapping
    public void establishRelation(@MappingTarget Order order) {
        order.setCustomer(customer);
    }
}
