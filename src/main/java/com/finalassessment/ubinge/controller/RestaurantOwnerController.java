package com.finalassessment.ubinge.controller;

import com.finalassessment.ubinge.model.Restaurant;
import com.finalassessment.ubinge.model.RestaurantOwner;
import com.finalassessment.ubinge.service.RestaurantOwnerService;
import com.finalassessment.ubinge.vo.GeneralDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<RestaurantOwner>> getAllRestaurantOwners() {
        log.debug("Getting all Restaurants Owners.");
        return ResponseEntity.status(HttpStatus.OK).body(restaurantOwnerService.findAll());
    }

    @GetMapping(value = "/restaurantowners/{restaurantOwnerId}")
    public ResponseEntity<RestaurantOwner> getRestaurantOwner(@PathVariable Long restaurantOwnerId) {
        log.debug("Getting Restaurant Owner by id.");
        return ResponseEntity.status(HttpStatus.OK).body(restaurantOwnerService.findById(restaurantOwnerId));
    }

    @GetMapping(value = "/restaurantowners/{restaurantOwnerId}/restaurants")
    public ResponseEntity<List<Restaurant>> getRestaurantOwnerRestaurants(@PathVariable Long restaurantOwnerId) {
        log.debug("Getting Restaurant Owner by id.");
        return ResponseEntity.status(HttpStatus.OK).body(restaurantOwnerService.findAllRestaurants(restaurantOwnerId));
    }

    @PostMapping(value = "/restaurantowners")
    public ResponseEntity<RestaurantOwner> saveRestaurantOwner(@RequestBody RestaurantOwner restaurantOwner) {
        log.debug("Saving Restaurant Owner.");
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantOwnerService.save(restaurantOwner));
    }

    @PostMapping(value = "/restaurantowners/{restaurantOwnerId}/restaurants")
    public ResponseEntity<RestaurantOwner> saveRestaurants(@PathVariable Long restaurantOwnerId, @RequestBody List<Restaurant> restaurants) {
        log.debug("Adding Restaurants to system - Restaurant Owners.");
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantOwnerService.saveRestaurants(restaurantOwnerId, restaurants));
    }


    @PutMapping(value = "/restaurantowners/{restaurantOwnerId}")
    public ResponseEntity<RestaurantOwner> updateRestaurantOwner(@RequestBody GeneralDetailVO generalDetailVO, @PathVariable Long restaurantOwnerId) {
        log.debug("Updating Restaurant Owner by Restaurant id.");
        return ResponseEntity.status(HttpStatus.OK).body(restaurantOwnerService.update(generalDetailVO, restaurantOwnerId));
    }

    @PutMapping(value = "/restaurantowners/{restaurantOwnerId}/restaurants/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurantDetails(@RequestBody GeneralDetailVO generalDetailVO, @PathVariable Long restaurantOwnerId, @PathVariable Long restaurantId) {
        log.debug("Updating Restaurant Owner by Restaurant id.");
        return ResponseEntity.status(HttpStatus.OK).body(restaurantOwnerService.updateRestaurantDetails(generalDetailVO, restaurantOwnerId, restaurantId));
    }

    @DeleteMapping(value = "/restaurantowners/{restaurantOwnerId}")
    public ResponseEntity<?> deleteRestaurantOwnerById(@PathVariable Long restaurantOwnerId) {
        log.debug("Deleting Restaurant Owner by Restaurant Owner id.");
        restaurantOwnerService.deleteById(restaurantOwnerId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/restaurantowners/{restaurantOwnerId}/restaurants")
    public ResponseEntity<?> deleteRestaurants(@PathVariable Long restaurantOwnerId, @RequestBody List<Long> restaurantIds) {
        log.debug("Removing Restaurants from system - Restaurant Owners");
        RestaurantOwner restaurantOwner = restaurantOwnerService.deleteRestaurants(restaurantOwnerId, restaurantIds);
        return ResponseEntity.noContent().build();
    }
}
