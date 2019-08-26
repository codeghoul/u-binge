package com.finalassessment.ubinge.service.impl;

import com.finalassessment.ubinge.exception.OrderFoodItemNotFoundException;
import com.finalassessment.ubinge.model.OrderFoodItem;
import com.finalassessment.ubinge.repository.OrderFoodItemRepository;
import com.finalassessment.ubinge.service.OrderFoodItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderFoodItemServiceImpl implements OrderFoodItemService {
    private OrderFoodItemRepository orderFoodItemRepository;

    @Autowired
    public OrderFoodItemServiceImpl(OrderFoodItemRepository orderFoodItemRepository) {
        this.orderFoodItemRepository = orderFoodItemRepository;
    }

    @Override
    public List<OrderFoodItem> findAll() {
        log.debug("Getting Order FoodItems from Service");
        return orderFoodItemRepository.findAll();
    }

    @Override
    public OrderFoodItem findById(Long orderFoodItemId) {
        log.debug("Getting Order FoodItem By Id from Service");
        return orderFoodItemRepository.findById(orderFoodItemId).orElseThrow(() -> new OrderFoodItemNotFoundException(orderFoodItemId));
    }

    @Override
    public OrderFoodItem save(OrderFoodItem newOrderFoodItem) {
        log.debug("Saving Order FoodItems from Service");
        return orderFoodItemRepository.save(newOrderFoodItem);
    }

    public OrderFoodItem update(OrderFoodItem orderFoodItem, Long orderFoodItemId) {
        log.debug("Updating Order FoodItem from Service");
        return null;
    }

    @Override
    public void delete(OrderFoodItem orderFoodItem) {
        log.debug("Deleting Order FoodItem from Service");
        orderFoodItemRepository.delete(orderFoodItem);
    }

    @Override
    public void deleteById(Long orderFoodItemId) {
        log.debug("Deleting Order FoodItem By Id from Service");
        orderFoodItemRepository.deleteById(orderFoodItemId);
    }
}