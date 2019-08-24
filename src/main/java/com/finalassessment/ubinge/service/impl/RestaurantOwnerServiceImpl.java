package com.finalassessment.ubinge.service.impl;

import com.finalassessment.ubinge.exception.RestaurantOwnerNotFoundException;
import com.finalassessment.ubinge.model.Restaurant;
import com.finalassessment.ubinge.model.RestaurantOwner;
import com.finalassessment.ubinge.repository.RestaurantOwnerRepository;
import com.finalassessment.ubinge.repository.RestaurantRepository;
import com.finalassessment.ubinge.service.RestaurantOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return restaurantOwnerRepository.findAll();
    }

    @Override
    public RestaurantOwner findById(Long restaurantOwnerId) {
        return restaurantOwnerRepository.findById(restaurantOwnerId).orElseThrow(() -> new RestaurantOwnerNotFoundException(restaurantOwnerId));
    }

    @Override
    public RestaurantOwner save(RestaurantOwner newRestaurantOwner) {
        return restaurantOwnerRepository.save(newRestaurantOwner);
    }

    @Override
    public RestaurantOwner update(RestaurantOwner restaurantOwner, Long restaurantOwnerId) {
        return null;
    }

    @Override
    public void delete(RestaurantOwner restaurantOwner) {
        restaurantOwnerRepository.delete(restaurantOwner);
    }

    @Override
    public void deleteById(Long restaurantOwnerId) {
        restaurantOwnerRepository.deleteById(restaurantOwnerId);
    }

    @Override
    public RestaurantOwner saveRestaurants(Long restaurantOwnerId, List<Restaurant> restaurants) {
        RestaurantOwner restaurantOwner = findById(restaurantOwnerId);
        restaurants.stream().forEach(restaurant -> {
            restaurant.setRestaurantOwner(restaurantOwner);
            restaurantRepository.save(restaurant);
        });
        restaurantOwner.getRestaurants().addAll(restaurants);
        return restaurantOwnerRepository.save(restaurantOwner);
    }
}