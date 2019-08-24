package com.finalassessment.ubinge.controller;

import com.finalassessment.ubinge.model.RestaurantOwner;
import com.finalassessment.ubinge.service.RestaurantOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantOwnerController {
    private RestaurantOwnerService restaurantOwnerService;

    @Autowired
    public RestaurantOwnerController(RestaurantOwnerService restaurantOwnerService) {
        this.restaurantOwnerService = restaurantOwnerService;
    }

    @GetMapping(value = "/restaurantowners")
    public List<RestaurantOwner> getAllRestaurantOwners() {
        return restaurantOwnerService.findAll();
    }

    @GetMapping(value = "/restaurantowners/{restaurantOwnerId}")
    public RestaurantOwner getRestaurantOwner(@PathVariable Long restaurantOwnerId) {
        return restaurantOwnerService.findById(restaurantOwnerId);
    }

    @PostMapping(value = "/restaurantowners")
    public RestaurantOwner saveRestaurantOwner(@RequestBody RestaurantOwner restaurantOwnerId) {
        return  restaurantOwnerService.save(restaurantOwnerId);
    }

    @PutMapping(value = "/restaurantowners/{restaurantOwnerId}")
    public RestaurantOwner updateRestaurantOwner(@RequestBody RestaurantOwner restaurantOwner, @PathVariable Long restaurantOwnerId) {
        return restaurantOwnerService.update(restaurantOwner, restaurantOwnerId);
    }

    @DeleteMapping(value = "/restaurantowners")
    public void deleteRestaurantOwner(@RequestBody RestaurantOwner restaurantOwner) {
        restaurantOwnerService.delete(restaurantOwner);
    }

    @DeleteMapping(value = "/restaurantowners/{restaurantOwnerId}")
    public void deleteRestaurantOwnerById(@PathVariable Long restaurantOwnerId) {
        restaurantOwnerService.deleteById(restaurantOwnerId);
    }
}
