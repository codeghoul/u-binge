package com.finalassessment.ubinge.controller;

import com.finalassessment.ubinge.model.Restaurant;
import com.finalassessment.ubinge.model.RestaurantOwner;
import com.finalassessment.ubinge.service.RestaurantOwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class RestaurantOwnerController {
    private RestaurantOwnerService restaurantOwnerService;

    @Autowired
    public RestaurantOwnerController(RestaurantOwnerService restaurantOwnerService) {
        this.restaurantOwnerService = restaurantOwnerService;
    }

    @GetMapping(value = "/restaurantowners")
    public List<RestaurantOwner> getAllRestaurantOwners() {
        log.debug("Getting all Restaurants Owners.");
        return restaurantOwnerService.findAll();
    }

    @GetMapping(value = "/restaurantowners/{restaurantOwnerId}")
    public RestaurantOwner getRestaurantOwner(@PathVariable Long restaurantOwnerId) {
        log.debug("Getting Restaurant Owner by id.");
        return restaurantOwnerService.findById(restaurantOwnerId);
    }

    @PostMapping(value = "/restaurantowners")
    public RestaurantOwner saveRestaurantOwner(@RequestBody RestaurantOwner restaurantOwner) {
        log.debug("Saving Restaurant Owner.");
        return restaurantOwnerService.save(restaurantOwner);
    }

    @PostMapping(value = "/restaurantowners/{restaurantOwnerId}/restaurants")
    public RestaurantOwner saveRestaurants(@PathVariable Long restaurantOwnerId, @RequestBody List<Restaurant> restaurants) {
        log.debug("Adding Restaurants to system - Restaurant Owners.");
        return restaurantOwnerService.saveRestaurants(restaurantOwnerId, restaurants);
    }

    @DeleteMapping(value = "/restaurantowners/{restaurantOwnerId}/restaurants")
    public RestaurantOwner deleteRestaurants(@PathVariable Long restaurantOwnerId, @RequestBody List<Long> restaurantIds) {
        log.debug("Removing Restaurants from system - Restaurant Owners");
        return restaurantOwnerService.deleteRestaurants(restaurantOwnerId, restaurantIds);
    }

    @PutMapping(value = "/restaurantowners/{restaurantOwnerId}")
    public RestaurantOwner updateRestaurantOwner(@RequestBody RestaurantOwner restaurantOwner, @PathVariable Long restaurantOwnerId) {
        log.debug("Updating Restaurant Owner by Restaurant id.");
        return restaurantOwnerService.update(restaurantOwner, restaurantOwnerId);
    }

    @DeleteMapping(value = "/restaurantowners")
    public void deleteRestaurantOwner(@RequestBody RestaurantOwner restaurantOwner) {
        log.debug("Deleting Restaurant Owner.");
        restaurantOwnerService.delete(restaurantOwner);
    }

    @DeleteMapping(value = "/restaurantowners/{restaurantOwnerId}")
    public void deleteRestaurantOwnerById(@PathVariable Long restaurantOwnerId) {
        log.debug("Deleting Restaurant Owner by Restaurant Owner id.");
        restaurantOwnerService.deleteById(restaurantOwnerId);
    }
}
