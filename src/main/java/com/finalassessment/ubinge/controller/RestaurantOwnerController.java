package com.finalassessment.ubinge.controller;

import com.finalassessment.ubinge.dto.RestaurantDTO;
import com.finalassessment.ubinge.dto.RestaurantOwnerDTO;
import com.finalassessment.ubinge.service.RestaurantOwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
/**
 * Controller to perform restaurant owner related operations.
 */
public class RestaurantOwnerController {
    private RestaurantOwnerService restaurantOwnerService;

    @Autowired
    public RestaurantOwnerController(RestaurantOwnerService restaurantOwnerService) {
        this.restaurantOwnerService = restaurantOwnerService;
    }

    /**
     * Returns list of all restaurant owners.
     *
     * @return
     */
    @GetMapping(value = "/restaurantowners")
    public ResponseEntity<List<RestaurantOwnerDTO>> getAllRestaurantOwners() {
        log.debug("Getting all Restaurants Owners.");
        return ResponseEntity.status(HttpStatus.OK).body(restaurantOwnerService.findAll());
    }

    /**
     * Returns restaurant owner belonging to given in path variable.
     * ThrowsRestaurantOwnerNotFound
     *
     * @param restaurantOwnerId
     * @return
     */
    @GetMapping(value = "/restaurantowners/{restaurantOwnerId}")
    public ResponseEntity<RestaurantOwnerDTO> getRestaurantOwner(@PathVariable Long restaurantOwnerId) {
        log.debug("Getting Restaurant Owner by id.");
        return ResponseEntity.status(HttpStatus.OK).body(restaurantOwnerService.findById(restaurantOwnerId));
    }

    /**
     * Returns list of restaurants belong to Restaurant Owner
     *
     * @param restaurantOwnerId
     * @return
     */
    @GetMapping(value = "/restaurantowners/{restaurantOwnerId}/restaurants")
    public ResponseEntity<List<RestaurantDTO>> getRestaurantOwnerRestaurants(@PathVariable Long restaurantOwnerId) {
        log.debug("Getting Restaurant Owner by id.");
        return ResponseEntity.status(HttpStatus.OK).body(restaurantOwnerService.findAllRestaurants(restaurantOwnerId));
    }

    /**
     * Persists a Restaurant Owner. Only Admin will have right to access this method.
     *
     * @param restaurantOwnerDTO
     * @return
     */
    @PostMapping(value = "/restaurantowners")
    public ResponseEntity<RestaurantOwnerDTO> saveRestaurantOwner(@Valid @RequestBody RestaurantOwnerDTO restaurantOwnerDTO) {
        log.debug("Saving Restaurant Owner.");
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantOwnerService.save(restaurantOwnerDTO));
    }

    /**
     * Persists a list of restaurant added by restaurant owner.
     *
     * @param restaurantOwnerId
     * @param restaurantDTOs
     * @return
     */
    //TODO: find way to validate when objects are in list form.
    @PostMapping(value = "/restaurantowners/{restaurantOwnerId}/restaurants")
    public ResponseEntity<RestaurantOwnerDTO> saveRestaurants(@PathVariable Long restaurantOwnerId, @RequestBody List<@Valid RestaurantDTO> restaurantDTOs) {
        log.debug("Adding Restaurants to system - Restaurant Owners.");
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantOwnerService.saveRestaurants(restaurantOwnerId, restaurantDTOs));
    }


    /**
     * Modify details of Restaurant Owner.
     *
     * @param restaurantOwnerDTO
     * @param restaurantOwnerId
     * @return
     */
    @PutMapping(value = "/restaurantowners/{restaurantOwnerId}")
    public ResponseEntity<RestaurantOwnerDTO> updateRestaurantOwner(@Valid @RequestBody RestaurantOwnerDTO restaurantOwnerDTO, @PathVariable Long restaurantOwnerId) {
        log.debug("Updating Restaurant Owner by Restaurant id.");
        return ResponseEntity.status(HttpStatus.OK).body(restaurantOwnerService.update(restaurantOwnerDTO, restaurantOwnerId));
    }

    /**
     * Updates restaurant details by restaurant owner.
     *
     * @param restaurantDTO
     * @param restaurantOwnerId
     * @param restaurantId
     * @return
     */
    @PutMapping(value = "/restaurantowners/{restaurantOwnerId}/restaurants/{restaurantId}")
    public ResponseEntity<RestaurantDTO> updateRestaurantDetails(@Valid @RequestBody RestaurantDTO restaurantDTO, @PathVariable Long restaurantOwnerId, @PathVariable Long restaurantId) {
        log.debug("Updating Restaurant Owner by Restaurant id.");
        return ResponseEntity.status(HttpStatus.OK).body(restaurantOwnerService.updateRestaurantDetails(restaurantDTO, restaurantOwnerId, restaurantId));
    }

    /**
     * Remove restaurant using restaurant id.
     *
     * @param restaurantOwnerId
     * @return
     */
    @DeleteMapping(value = "/restaurantowners/{restaurantOwnerId}")
    public ResponseEntity<?> deleteRestaurantOwnerById(@PathVariable Long restaurantOwnerId) {
        log.debug("Deleting Restaurant Owner by Restaurant Owner id.");
        restaurantOwnerService.deleteById(restaurantOwnerId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Delete list of restaurants with provided restaurant ids.
     *
     * @param restaurantOwnerId
     * @param restaurantIds
     * @return
     */
    @DeleteMapping(value = "/restaurantowners/{restaurantOwnerId}/restaurants")
    public ResponseEntity<?> deleteRestaurants(@PathVariable Long restaurantOwnerId, @Valid @RequestBody List<Long> restaurantIds) {
        log.debug("Removing Restaurants from system - Restaurant Owners");
        RestaurantOwnerDTO restaurantOwnerDTO = restaurantOwnerService.deleteRestaurants(restaurantOwnerId, restaurantIds);
        return ResponseEntity.noContent().build();
    }
}
