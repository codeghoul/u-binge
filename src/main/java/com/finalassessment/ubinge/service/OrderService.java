package com.finalassessment.ubinge.service;

import com.finalassessment.ubinge.model.Order;
import com.finalassessment.ubinge.vo.OrderVO;

public interface OrderService extends CrudService<Order, Long> {
    Order createOrder(OrderVO orderVO);
}
