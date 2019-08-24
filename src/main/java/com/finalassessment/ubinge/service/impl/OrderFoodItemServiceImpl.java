package com.finalassessment.ubinge.service.impl;

import com.finalassessment.ubinge.exception.OrderFoodItemNotFoundException;
import com.finalassessment.ubinge.model.OrderFoodItem;
import com.finalassessment.ubinge.repository.OrderFoodItemRepository;
import com.finalassessment.ubinge.service.OrderFoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderFoodItemServiceImpl implements OrderFoodItemService {
    private OrderFoodItemRepository orderFoodItemRepository;

    @Autowired
    public OrderFoodItemServiceImpl(OrderFoodItemRepository orderFoodItemRepository) {
        this.orderFoodItemRepository = orderFoodItemRepository;
    }

    @Override
    public List<OrderFoodItem> findAll() {
        return orderFoodItemRepository.findAll();
    }

    @Override
    public OrderFoodItem findById(Long orderFoodItemId) {
        return orderFoodItemRepository.findById(orderFoodItemId).orElseThrow(() -> new OrderFoodItemNotFoundException(orderFoodItemId));
    }

    @Override
    public OrderFoodItem save(OrderFoodItem newOrderFoodItem) {
        return orderFoodItemRepository.save(newOrderFoodItem);
    }

    @Override
    public OrderFoodItem update(OrderFoodItem orderFoodItem, Long orderFoodItemId) {
        return null;
    }

    @Override
    public void delete(OrderFoodItem orderFoodItem) {
        orderFoodItemRepository.delete(orderFoodItem);
    }

    @Override
    public void deleteById(Long orderFoodItemId) {
        orderFoodItemRepository.deleteById(orderFoodItemId);
    }
}