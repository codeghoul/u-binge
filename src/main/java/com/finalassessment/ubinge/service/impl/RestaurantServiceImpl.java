package com.finalassessment.ubinge.service.impl;

import com.finalassessment.ubinge.exception.RestaurantNotFoundException;
import com.finalassessment.ubinge.model.Restaurant;
import com.finalassessment.ubinge.repository.RestaurantRepository;
import com.finalassessment.ubinge.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RestaurantServiceImpl implements RestaurantService {
    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Restaurant> findAll() {
        log.debug("Getting Restaurants from Service.");
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant findById(Long restaurantId) {
        log.debug("Getting Restaurant By Id from Service.");
        return restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
    }

    @Override
    public Restaurant save(Restaurant newRestaurant) {
        log.debug("Saving Restaurant from Service.");
        return restaurantRepository.save(newRestaurant);
    }

    @Override
    public Restaurant update(Restaurant restaurant, Long restaurantId) {
        log.debug("Updating Restaurant from Service.");
        return null;
    }

    @Override
    public void delete(Restaurant restaurant) {
        log.debug("Deleting Restaurant from Service.");
        restaurantRepository.delete(restaurant);
    }

    @Override
    public void deleteById(Long restaurantId) {
        log.debug("Deleting Restaurant By Id from Service.");
        restaurantRepository.deleteById(restaurantId);
    }
}