package com.finalassessment.ubinge.service.impl;

import com.finalassessment.ubinge.exception.OrderNotFoundException;
import com.finalassessment.ubinge.model.Order;
import com.finalassessment.ubinge.repository.OrderRepository;
import com.finalassessment.ubinge.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    @Override
    public Order save(Order newOrder) {
        return orderRepository.save(newOrder);
    }

    @Override
    public Order update(Order order, Long orderId) {
        return null;
    }

    @Override
    public void delete(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public void deleteById(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}