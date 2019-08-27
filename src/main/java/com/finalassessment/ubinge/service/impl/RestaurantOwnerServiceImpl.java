package com.finalassessment.ubinge.service.impl;

import com.finalassessment.ubinge.exception.RestaurantNotFoundException;
import com.finalassessment.ubinge.exception.RestaurantOwnerNotFoundException;
import com.finalassessment.ubinge.model.Restaurant;
import com.finalassessment.ubinge.model.RestaurantOwner;
import com.finalassessment.ubinge.repository.RestaurantOwnerRepository;
import com.finalassessment.ubinge.repository.RestaurantRepository;
import com.finalassessment.ubinge.service.RestaurantOwnerService;
import com.finalassessment.ubinge.vo.GeneralDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RestaurantOwnerServiceImpl implements RestaurantOwnerService {
    private RestaurantOwnerRepository restaurantOwnerRepository;
    private RestaurantRepository restaurantRepository;

    @Contract(pure = true)
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
    public List<Restaurant> findAllRestaurants(Long restaurantOwnerId) {
        RestaurantOwner restaurantOwner = findById(restaurantOwnerId);
        return restaurantOwner.getRestaurants().stream().collect(Collectors.toList());
    }

    @Override
    public RestaurantOwner save(RestaurantOwner newRestaurantOwner) {
        log.debug("Saving Restaurant Owner from Service.");
        return restaurantOwnerRepository.save(newRestaurantOwner);
    }

    @Override
    public RestaurantOwner update(GeneralDetailVO generalDetailVO, Long restaurantOwnerId) {
        log.debug("Updating Restaurant Owner from Service.");
        RestaurantOwner restaurantOwner = findById(restaurantOwnerId);
        restaurantOwner.setName(generalDetailVO.getName());
        restaurantOwner.setEmail(generalDetailVO.getEmail());
        restaurantOwner.setPhoneNo(generalDetailVO.getPhoneNo());
        return restaurantOwnerRepository.saveAndFlush(restaurantOwner);
    }

    @Override
    public Restaurant updateRestaurantDetails(GeneralDetailVO generalDetailVO, Long restaurantOwnerId, Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
        RestaurantOwner restaurantOwner = findById(restaurantOwnerId);
        if (!restaurantOwner.getRestaurants().contains(restaurant)) {
            throw new IllegalArgumentException("You don't own restaurant: " + restaurant.getName());
        }
        restaurant.setName(generalDetailVO.getName());
        restaurant.setEmail(generalDetailVO.getEmail());
        restaurant.setPhoneNo(generalDetailVO.getPhoneNo());
        return restaurantRepository.saveAndFlush(restaurant);
    }

    @Override
    public void deleteById(Long restaurantOwnerId) {
        log.debug("Deleting Restaurant Owner by Id from Service.");
        restaurantOwnerRepository.deleteById(restaurantOwnerId);
    }

    @Override
    public RestaurantOwner saveRestaurants(Long restaurantOwnerId, @NotNull List<Restaurant> restaurants) {
        log.debug("Adding Restaurant Owner to Restaurants and Vice Versa from Service.");
        RestaurantOwner restaurantOwner = findById(restaurantOwnerId);
        restaurants.stream().forEach(restaurant -> {
            restaurant.setRestaurantOwner(restaurantOwner);
            restaurantRepository.save(restaurant);
        });
        return restaurantOwnerRepository.save(restaurantOwner);
    }

    @Override
    public RestaurantOwner deleteRestaurants(Long restaurantOwnerId, List<Long> restaurantIds) {
        log.debug("Removing Restaurants from Restaurant Owner and Vice Versa from Service.");
        RestaurantOwner restaurantOwner = findById(restaurantOwnerId);
        List<Restaurant> restaurants = restaurantRepository.findAllById(restaurantIds);
        restaurantRepository.deleteAll(restaurants);
//        restaurants.stream().forEach(restaurantRepository::delete);
        return restaurantOwner;
    }
}