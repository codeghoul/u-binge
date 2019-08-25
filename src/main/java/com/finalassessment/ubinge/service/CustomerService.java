package com.finalassessment.ubinge.service;

import com.finalassessment.ubinge.model.Customer;
import com.finalassessment.ubinge.model.Order;

import java.util.List;

public interface CustomerService extends CrudService<Customer, Long> {
    List<Order> getCustomerOrders(Long customerId);
}
