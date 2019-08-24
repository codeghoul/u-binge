package com.finalassessment.ubinge.service.impl;

import com.finalassessment.ubinge.exception.RestaurantNotFoundException;
import com.finalassessment.ubinge.model.Restaurant;
import com.finalassessment.ubinge.repository.RestaurantRepository;
import com.finalassessment.ubinge.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant findById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
    }

    @Override
    public Restaurant save(Restaurant newRestaurant) {
        return restaurantRepository.save(newRestaurant);
    }

    @Override
    public Restaurant update(Restaurant restaurant, Long restaurantId) {
        return null;
    }

    @Override
    public void delete(Restaurant restaurant) {
        restaurantRepository.delete(restaurant);
    }

    @Override
    public void deleteById(Long restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }
}