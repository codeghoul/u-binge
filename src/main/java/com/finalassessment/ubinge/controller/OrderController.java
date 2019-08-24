package com.finalassessment.ubinge.controller;

import com.finalassessment.ubinge.model.Order;
import com.finalassessment.ubinge.service.OrderService;
import com.finalassessment.ubinge.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public Order createOrder(@RequestBody OrderVO orderVO) {
        System.out.println(orderVO);
        log.debug(String.valueOf(orderVO));
        return orderService.createOrder(orderVO);
    }
}
