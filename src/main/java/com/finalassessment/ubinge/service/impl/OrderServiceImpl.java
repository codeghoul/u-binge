package com.finalassessment.ubinge.service.impl;

import com.finalassessment.ubinge.exception.OrderNotFoundException;
import com.finalassessment.ubinge.model.Order;
import com.finalassessment.ubinge.repository.OrderRepository;
import com.finalassessment.ubinge.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll() {
        log.debug("Getting Orders from Service.");
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long orderId) {
        log.debug("Getting Order by Id from Service.");
        return orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    @Override
    public Order save(Order newOrder) {
        log.debug("Saving order from Service.");
        return orderRepository.save(newOrder);
    }

    @Override
    public Order update(Order order, Long orderId) {
        log.debug("Updating Order from Service.");
        return null;
    }

    @Override
    public void delete(Order order) {
        log.debug("Deleting order from Service.");
        orderRepository.delete(order);
    }

    @Override
    public void deleteById(Long orderId) {
        log.debug("Deleting order by id from Service.");
        orderRepository.deleteById(orderId);
    }
}