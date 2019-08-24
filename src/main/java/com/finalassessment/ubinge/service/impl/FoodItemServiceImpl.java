package com.finalassessment.ubinge.service.impl;

import com.finalassessment.ubinge.exception.FoodItemNotFoundException;
import com.finalassessment.ubinge.model.FoodItem;
import com.finalassessment.ubinge.repository.FoodItemRepository;
import com.finalassessment.ubinge.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemServiceImpl implements FoodItemService {
    private FoodItemRepository foodItemRepository;

    @Autowired
    public FoodItemServiceImpl(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    @Override
    public List<FoodItem> findAll() {
        return foodItemRepository.findAll();
    }

    @Override
    public FoodItem findById(Long foodItemId) {
        return foodItemRepository.findById(foodItemId).orElseThrow(() -> new FoodItemNotFoundException(foodItemId));
    }

    @Override
    public FoodItem save(FoodItem newFoodItem) {
        return foodItemRepository.save(newFoodItem);
    }

    @Override
    public FoodItem update(FoodItem foodItem, Long foodItemId) {
        return null;
    }

    @Override
    public void delete(FoodItem foodItem) {
        foodItemRepository.delete(foodItem);
    }

    @Override
    public void deleteById(Long foodItemId) {
        foodItemRepository.deleteById(foodItemId);
    }
}