package com.finalassessment.ubinge.service.impl;

import com.finalassessment.ubinge.exception.RestaurantNotFoundException;
import com.finalassessment.ubinge.model.FoodItem;
import com.finalassessment.ubinge.model.Order;
import com.finalassessment.ubinge.model.Restaurant;
import com.finalassessment.ubinge.repository.FoodItemRepository;
import com.finalassessment.ubinge.repository.RestaurantRepository;
import com.finalassessment.ubinge.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RestaurantServiceImpl implements RestaurantService {
    private RestaurantRepository restaurantRepository;
    private FoodItemRepository foodItemRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, FoodItemRepository foodItemRepository) {
        this.restaurantRepository = restaurantRepository;
        this.foodItemRepository = foodItemRepository;
    }

    @Override
    public List<Restaurant> findAll() {
        log.debug("Getting Restaurants from Service.");
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant findById(Long restaurantId) {
        log.debug("Getting Restaurant By Id from Service.");
        return restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
    }

    @Override
    public Restaurant save(Restaurant newRestaurant) {
        log.debug("Saving Restaurant from Service.");
        return restaurantRepository.save(newRestaurant);
    }

    public Restaurant update(Restaurant restaurant, Long restaurantId) {
        log.debug("Updating Restaurant from Service.");
        return null;
    }

    @Override
    public void delete(Restaurant restaurant) {
        log.debug("Deleting Restaurant from Service.");
        restaurantRepository.delete(restaurant);
    }

    @Override
    public void deleteById(Long restaurantId) {
        log.debug("Deleting Restaurant By Id from Service.");
        restaurantRepository.deleteById(restaurantId);
    }

    @Override
    public Restaurant addFoodItems(Long restaurantId, @org.jetbrains.annotations.NotNull List<FoodItem> foodItems) {
        Restaurant restaurant = findById(restaurantId);
        log.debug("Adding Food Items to Restaurant "+ restaurant.getName() + " from Service. ");
        foodItems.stream().forEach(foodItem -> {
            foodItem.setRestaurant(restaurant);
            foodItemRepository.save(foodItem);
        });
        return restaurant;
    }

    @Override
    public Restaurant removeFoodItems(Long restaurantId, List<Long> foodItemIds) {
        Restaurant restaurant = findById(restaurantId);
        log.debug("Removing Food Items from Restaurant" + restaurant.getName() + " from Service. ");
        List<FoodItem> foodItems = foodItemRepository.findAllById(foodItemIds);
        foodItemRepository.deleteAll(foodItems);
        return restaurant;
    }

    @Override
    public List<Order> getRestaurantOrders(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
        return restaurant.getOrders().stream().collect(Collectors.toList());
    }
}