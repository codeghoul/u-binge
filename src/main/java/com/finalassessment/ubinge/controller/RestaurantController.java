package com.finalassessment.ubinge.controller;

import com.finalassessment.ubinge.dto.FoodItemDTO;
import com.finalassessment.ubinge.dto.OrderDTO;
import com.finalassessment.ubinge.dto.OrderModificationDTO;
import com.finalassessment.ubinge.dto.RestaurantDTO;
import com.finalassessment.ubinge.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<?> getAllRestaurants() {
        log.debug("Getting all Restaurants");
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.findAll());
    }

    @GetMapping(value = "/restaurants/{restaurantId}")
    public ResponseEntity<RestaurantDTO> getRestaurant(@PathVariable Long restaurantId) {
        log.debug("Getting Restaurant by id.");
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.findById(restaurantId));
    }

    @GetMapping(value = "/restaurants/{restaurantId}/fooditems")
    public ResponseEntity<List<FoodItemDTO>> getRestaurantFoodItems(@PathVariable Long restaurantId) {
        log.debug("Getting all FoodItems using restaurantId.");
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getRestaurantFoodItems(restaurantId));
    }

    @GetMapping(value = "/restaurants/{restaurantId}/orders")
    public ResponseEntity<List<OrderDTO>> getRestaurantOrders(@PathVariable Long restaurantId) {
        log.debug("Getting all Orders using restaurantId.");
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getRestaurantOrders(restaurantId));
    }

    @GetMapping(value = "/restaurants/{restaurantId}/orders/{orderId}")
    public ResponseEntity<OrderDTO> getRestaurantOrderById(@PathVariable Long restaurantId, @PathVariable Long orderId) {
        log.debug("Getting Restaurant Order By Restaurant Id.");
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getRestaurantOrderById(restaurantId, orderId));
    }

    @PostMapping(value = "/restaurants/{restaurantId}/fooditems")
    public ResponseEntity<RestaurantDTO> addFoodItems(@PathVariable Long restaurantId, @Valid @RequestBody List<FoodItemDTO> foodItemDTOs) {
        log.debug("Adding food items to restaurants.");
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.addFoodItems(restaurantId, foodItemDTOs));
    }

    @PutMapping(value = "/restaurants/{restaurantId}/orders/{orderId}")
    public ResponseEntity<OrderDTO> modifyOrder(@PathVariable Long restaurantId, @PathVariable Long orderId, @Valid @RequestBody OrderModificationDTO modification) {
        log.debug("Modifying Customer Order.");
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.modifyOrder(restaurantId, orderId, modification));
    }

    @DeleteMapping(value = "/restaurants/{restaurantId}/fooditems")
    public ResponseEntity<?> removeFoodItems(@PathVariable Long restaurantId, @Valid @RequestBody List<Long> foodItemIds) {
        log.debug("Removing food items from restaurants");
        restaurantService.removeFoodItems(restaurantId, foodItemIds);
        return ResponseEntity.noContent().build();
    }
}
