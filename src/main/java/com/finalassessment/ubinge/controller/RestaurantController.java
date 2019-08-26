package com.finalassessment.ubinge.controller;

import com.finalassessment.ubinge.model.FoodItem;
import com.finalassessment.ubinge.model.Order;
import com.finalassessment.ubinge.model.Restaurant;
import com.finalassessment.ubinge.service.RestaurantService;
import com.finalassessment.ubinge.vo.OrderModificationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "/restaurants/{restaurantId}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable Long restaurantId) {
        log.debug("Getting Restaurant by id.");
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.findById(restaurantId));
    }

    @GetMapping(value = "/restaurants/{restaurantId}/orders")
    public ResponseEntity<List<Order>> getRestaurantOrders(@PathVariable Long restaurantId) {
        log.debug("Getting all Orders using restaurantId.");
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getRestaurantOrders(restaurantId));
    }

    @GetMapping(value = "/restaurants/{restaurantId}/orders/{orderId}")
    public ResponseEntity<Order> getRestaurantOrderById(@PathVariable Long restaurantId, @PathVariable Long orderId) {
        log.debug("Getting Restaurant Order By Restaurant Id.");
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.getRestaurantOrderById(restaurantId, orderId));
    }

    @PostMapping(value = "/restaurants/{restaurantId}/fooditems")
    public ResponseEntity<Restaurant> addFoodItems(@PathVariable Long restaurantId, @RequestBody List<FoodItem> foodItems) {
        log.debug("Adding food items to restaurants.");
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.addFoodItems(restaurantId, foodItems));
    }

    @PutMapping(value = "/restaurants/{restaurantId}/orders/{orderId}")
    public ResponseEntity<Order> modifyOrder(@PathVariable Long restaurantId, @PathVariable Long orderId, @RequestBody OrderModificationVO modification) {
        log.debug("Modifying Customer Order.");
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.modifyOrder(restaurantId, orderId, modification));
    }

    @DeleteMapping(value = "/restaurants/{restaurantId}/fooditems")
    public ResponseEntity<?> removeFoodItems(@PathVariable Long restaurantId, @RequestBody List<Long> foodItemIds) {
        log.debug("Removing food items from restaurants");
        restaurantService.removeFoodItems(restaurantId, foodItemIds);
        return ResponseEntity.noContent().build();
    }
}
