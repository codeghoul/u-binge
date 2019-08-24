package com.finalassessment.ubinge.controller;

import com.finalassessment.ubinge.model.FoodItem;
import com.finalassessment.ubinge.model.Restaurant;
import com.finalassessment.ubinge.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class RestaurantController {
    private RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping(value = "/restaurants")
    public List<Restaurant> getAllRestaurants() {
        log.debug("Getting all Restaurants.");
        return restaurantService.findAll();
    }

    @GetMapping(value = "/restaurants/{restaurantId}")
    public Restaurant getRestaurant(@PathVariable Long restaurantId) {
        log.debug("Getting Restaurant by id.");
        return restaurantService.findById(restaurantId);
    }

//    @PostMapping(value = "/restaurants")
//    public Restaurant saveRestaurant(@RequestBody Restaurant restaurant) {
//        return  restaurantService.save(restaurant);
//    }

//    @PutMapping(value = "/restaurants/{restaurantId}")
//    public Restaurant updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable Long restaurantId) {
//        return restaurantService.update(restaurant, restaurantId);
//    }

//    @DeleteMapping(value = "/restaurants")
//    public void deleteRestaurant(@RequestBody Restaurant restaurant) {
//        restaurantService.delete(restaurant);
//    }

//    @DeleteMapping(value = "/restaurants/{restaurantId}")
//    public void deleteRestaurantById(@PathVariable Long restaurantId) {
//        restaurantService.deleteById(restaurantId);
//    }

    @PostMapping(value = "/restaurants/{restaurantId}/fooditems")
    public Restaurant addFoodItems(@PathVariable Long restaurantId, @RequestBody List<FoodItem> foodItems) {
        log.debug("Adding food items to restaurants.");
        return restaurantService.addFoodItems(restaurantId, foodItems);
    }
}
