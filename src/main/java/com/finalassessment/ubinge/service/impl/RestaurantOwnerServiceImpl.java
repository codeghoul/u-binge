package com.finalassessment.ubinge.service.impl;

import com.finalassessment.ubinge.exception.RestaurantOwnerNotFoundException;
import com.finalassessment.ubinge.model.Restaurant;
import com.finalassessment.ubinge.model.RestaurantOwner;
import com.finalassessment.ubinge.repository.RestaurantOwnerRepository;
import com.finalassessment.ubinge.repository.RestaurantRepository;
import com.finalassessment.ubinge.service.RestaurantOwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RestaurantOwnerServiceImpl implements RestaurantOwnerService {
    private RestaurantOwnerRepository restaurantOwnerRepository;
    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantOwnerServiceImpl(RestaurantOwnerRepository restaurantOwnerRepository, RestaurantRepository restaurantRepository) {
        this.restaurantOwnerRepository = restaurantOwnerRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<RestaurantOwner> findAll() {
        log.debug("Getting Restaurant Owners from Service.");
        return restaurantOwnerRepository.findAll();
    }

    @Override
    public RestaurantOwner findById(Long restaurantOwnerId) {
        log.debug("Getting Restaurant Owner by Id from Service.");
        return restaurantOwnerRepository.findById(restaurantOwnerId).orElseThrow(() -> new RestaurantOwnerNotFoundException(restaurantOwnerId));
    }

    @Override
    public RestaurantOwner save(RestaurantOwner newRestaurantOwner) {
        log.debug("Saving Restaurant Owner from Service.");
        return restaurantOwnerRepository.save(newRestaurantOwner);
    }

    @Override
    public RestaurantOwner update(RestaurantOwner restaurantOwner, Long restaurantOwnerId) {
        log.debug("Updating Restaurant Owner from Service.");
        return null;
    }

    @Override
    public void delete(RestaurantOwner restaurantOwner) {
        log.debug("Deleting Restaurant Owner from Service.");
        restaurantOwnerRepository.delete(restaurantOwner);
    }

    @Override
    public void deleteById(Long restaurantOwnerId) {
        log.debug("Deleting Restaurant Owner by Id from Service.");
        restaurantOwnerRepository.deleteById(restaurantOwnerId);
    }

    @Override
    public RestaurantOwner saveRestaurants(Long restaurantOwnerId, List<Restaurant> restaurants) {
        log.debug("Adding Restaurant Owner to Restaurants and Vice Versa from Service.");
        RestaurantOwner restaurantOwner = findById(restaurantOwnerId);
        restaurants.stream().forEach(restaurant -> {
            restaurant.setRestaurantOwner(restaurantOwner);
            restaurantRepository.save(restaurant);
        });
        restaurantOwner.getRestaurants().addAll(restaurants);
        return restaurantOwnerRepository.save(restaurantOwner);
    }
}