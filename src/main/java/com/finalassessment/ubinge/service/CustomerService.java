package com.finalassessment.ubinge.service;

import com.finalassessment.ubinge.dto.CustomerDTO;
import com.finalassessment.ubinge.dto.OrderDTO;

import java.util.List;

public interface CustomerService extends CrudService<CustomerDTO, Long> {
    CustomerDTO update(CustomerDTO customerDTO, Long customerId);

    List<OrderDTO> getCustomerOrders(Long customerId);

    OrderDTO getCustomerOrderById(Long customerId, Long orderId);
}
