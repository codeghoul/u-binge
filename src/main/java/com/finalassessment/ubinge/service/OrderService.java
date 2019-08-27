package com.finalassessment.ubinge.service;

import com.finalassessment.ubinge.dto.OrderDTO;

public interface OrderService extends CrudService<OrderDTO, Long> {
    OrderDTO createOrder(OrderDTO orderDTO);
}
