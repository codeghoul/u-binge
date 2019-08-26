package com.finalassessment.ubinge.service;

import com.finalassessment.ubinge.model.Customer;
import com.finalassessment.ubinge.model.Order;
import com.finalassessment.ubinge.vo.GeneralDetailVO;
import com.finalassessment.ubinge.vo.OrderModificationVO;

import java.util.List;

public interface CustomerService extends CrudService<Customer, Long> {
    List<Order> getCustomerOrders(Long customerId);

    Order modifyOrder(Long customerId, Long orderId, OrderModificationVO modification);

    Order getCustomerOrderById(Long customerId, Long orderId);

    Customer update(GeneralDetailVO generalDetailVO, Long customerId);
}
