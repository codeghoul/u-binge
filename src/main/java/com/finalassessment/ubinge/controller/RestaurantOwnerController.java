package com.finalassessment.ubinge.controller;

import com.finalassessment.ubinge.dto.RestaurantDTO;
import com.finalassessment.ubinge.dto.RestaurantOwnerDTO;
import com.finalassessment.ubinge.service.RestaurantOwnerService;
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
    public ResponseEntity<List<RestaurantOwnerDTO>> getAllRestaurantOwners() {
        log.debug("Getting all Restaurants Owners.");
        return ResponseEntity.status(HttpStatus.OK).body(restaurantOwnerService.findAll());
    }

    @GetMapping(value = "/restaurantowners/{restaurantOwnerId}")
    public ResponseEntity<RestaurantOwnerDTO> getRestaurantOwner(@PathVariable Long restaurantOwnerId) {
        log.debug("Getting Restaurant Owner by id.");
        return ResponseEntity.status(HttpStatus.OK).body(restaurantOwnerService.findById(restaurantOwnerId));
    }

    @GetMapping(value = "/restaurantowners/{restaurantOwnerId}/restaurants")
    public ResponseEntity<List<RestaurantDTO>> getRestaurantOwnerRestaurants(@PathVariable Long restaurantOwnerId) {
        log.debug("Getting Restaurant Owner by id.");
        return ResponseEntity.status(HttpStatus.OK).body(restaurantOwnerService.findAllRestaurants(restaurantOwnerId));
    }

    @PostMapping(value = "/restaurantowners")
    public ResponseEntity<RestaurantOwnerDTO> saveRestaurantOwner(@RequestBody RestaurantOwnerDTO restaurantOwnerDTO) {
        log.debug("Saving Restaurant Owner.");
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantOwnerService.save(restaurantOwnerDTO));
    }

    @PostMapping(value = "/restaurantowners/{restaurantOwnerId}/restaurants")
    public ResponseEntity<RestaurantOwnerDTO> saveRestaurants(@PathVariable Long restaurantOwnerId, @RequestBody List<RestaurantDTO> restaurantDTOs) {
        log.debug("Adding Restaurants to system - Restaurant Owners.");
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantOwnerService.saveRestaurants(restaurantOwnerId, restaurantDTOs));
    }


    @PutMapping(value = "/restaurantowners/{restaurantOwnerId}")
    public ResponseEntity<RestaurantOwnerDTO> updateRestaurantOwner(@RequestBody RestaurantOwnerDTO restaurantOwnerDTO, @PathVariable Long restaurantOwnerId) {
        log.debug("Updating Restaurant Owner by Restaurant id.");
        return ResponseEntity.status(HttpStatus.OK).body(restaurantOwnerService.update(restaurantOwnerDTO, restaurantOwnerId));
    }

    @PutMapping(value = "/restaurantowners/{restaurantOwnerId}/restaurants/{restaurantId}")
    public ResponseEntity<RestaurantDTO> updateRestaurantDetails(@RequestBody RestaurantDTO restaurantDTO, @PathVariable Long restaurantOwnerId, @PathVariable Long restaurantId) {
        log.debug("Updating Restaurant Owner by Restaurant id.");
        return ResponseEntity.status(HttpStatus.OK).body(restaurantOwnerService.updateRestaurantDetails(restaurantDTO, restaurantOwnerId, restaurantId));
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
        RestaurantOwnerDTO restaurantOwnerDTO = restaurantOwnerService.deleteRestaurants(restaurantOwnerId, restaurantIds);
        return ResponseEntity.noContent().build();
    }
}
