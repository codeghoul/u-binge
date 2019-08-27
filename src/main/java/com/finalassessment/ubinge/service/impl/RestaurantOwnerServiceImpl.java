package com.finalassessment.ubinge.service.impl;

import com.finalassessment.ubinge.dto.RestaurantDTO;
import com.finalassessment.ubinge.dto.RestaurantOwnerDTO;
import com.finalassessment.ubinge.exception.RestaurantNotFoundException;
import com.finalassessment.ubinge.exception.RestaurantOwnerNotFoundException;
import com.finalassessment.ubinge.model.Restaurant;
import com.finalassessment.ubinge.model.RestaurantOwner;
import com.finalassessment.ubinge.repository.RestaurantOwnerRepository;
import com.finalassessment.ubinge.repository.RestaurantRepository;
import com.finalassessment.ubinge.service.RestaurantOwnerService;
import com.finalassessment.ubinge.utility.MapperUtil;
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
    public List<RestaurantOwnerDTO> findAll() {
        log.debug("Getting Restaurant Owners from Service.");
        return restaurantOwnerRepository.findAll().stream().map(MapperUtil :: toRestaurantOwnerDTO).collect(Collectors.toList());
    }

    @Override
    public RestaurantOwnerDTO findById(Long restaurantOwnerId) {
        log.debug("Getting Restaurant Owner by Id from Service.");
        return MapperUtil.toRestaurantOwnerDTO(getRestaurantOwner(restaurantOwnerId));
    }

    @Override
    public List<RestaurantDTO> findAllRestaurants(Long restaurantOwnerId) {
        log.debug("Getting all Restaurants");
        RestaurantOwner restaurantOwner = getRestaurantOwner(restaurantOwnerId);
        List<Restaurant> restaurants = restaurantOwner.getRestaurants().stream().collect(Collectors.toList());
        return restaurants.stream().map(MapperUtil :: toRestaurantDTO).collect(Collectors.toList());
    }

    @Override
    public RestaurantOwnerDTO save(RestaurantOwnerDTO restaurantOwnerDTO) {
        log.debug("Saving Restaurant Owner from Service.");
        RestaurantOwner restaurantOwner = MapperUtil.toRestaurantOwner(restaurantOwnerDTO);
        return MapperUtil.toRestaurantOwnerDTO(restaurantOwnerRepository.save(restaurantOwner));
    }

    @Override
    public RestaurantOwnerDTO update(RestaurantOwnerDTO restaurantOwnerDTO, Long restaurantOwnerId) {
        log.debug("Updating Restaurant Owner from Service.");
        RestaurantOwner restaurantOwner = getRestaurantOwner(restaurantOwnerId);
        restaurantOwner.setName(restaurantOwnerDTO.getName());
        restaurantOwner.setEmail(restaurantOwnerDTO.getEmail());
        restaurantOwner.setPhoneNo(restaurantOwnerDTO.getPhoneNo());
        restaurantOwner.setPassword(restaurantOwnerDTO.getPassword());
        restaurantOwner.setRole("OWNER");
        return MapperUtil.toRestaurantOwnerDTO(restaurantOwnerRepository.saveAndFlush(restaurantOwner));
    }

    @Override
    public RestaurantDTO updateRestaurantDetails(RestaurantDTO restaurantDTO, Long restaurantOwnerId, Long restaurantId) {
        Restaurant restaurant = getRestaurant(restaurantId);
        RestaurantOwner restaurantOwner = getRestaurantOwner(restaurantOwnerId);
        if (!restaurantOwner.getRestaurants().contains(restaurant)) {
            throw new IllegalArgumentException("You don't own restaurant: " + restaurant.getName());
        }
        restaurant.setName(restaurantDTO.getName());
        restaurant.setEmail(restaurantDTO.getEmail());
        restaurant.setPhoneNo(restaurantDTO.getPhoneNo());
        restaurant.setPassword(restaurantDTO.getPassword());
        restaurant.setRole("RESTRO");
        return MapperUtil.toRestaurantDTO(restaurantRepository.saveAndFlush(restaurant));
    }

    @Override
    public void deleteById(Long restaurantOwnerId) {
        log.debug("Deleting Restaurant Owner by Id from Service.");
        restaurantOwnerRepository.deleteById(restaurantOwnerId);
    }

    @Override
    public RestaurantOwnerDTO saveRestaurants(Long restaurantOwnerId, @NotNull List<RestaurantDTO> restaurantDTOs) {
        log.debug("Adding Restaurant Owner to Restaurants and Vice Versa from Service.");
        RestaurantOwner restaurantOwner = getRestaurantOwner(restaurantOwnerId);
        restaurantDTOs.stream().forEach(restaurantDTO -> {
            Restaurant restaurant = MapperUtil.toRestaurant(restaurantDTO);
            restaurant.setRestaurantOwner(restaurantOwner);
            restaurantRepository.save(restaurant);
        });
        return MapperUtil.toRestaurantOwnerDTO(restaurantOwner);
    }

    @Override
    public RestaurantOwnerDTO deleteRestaurants(Long restaurantOwnerId, List<Long> restaurantIds) {
        //TODO: possible security breach.
        log.debug("Removing Restaurants from Restaurant Owner and Vice Versa from Service.");
        RestaurantOwner restaurantOwner = getRestaurantOwner(restaurantOwnerId);
        List<Restaurant> restaurants = restaurantRepository.findAllById(restaurantIds);
        restaurantRepository.deleteAll(restaurants);
        return MapperUtil.toRestaurantOwnerDTO(restaurantOwner);
    }

    private RestaurantOwner getRestaurantOwner(Long restaurantOwnerId) {
        return restaurantOwnerRepository.findById(restaurantOwnerId).orElseThrow(() -> new RestaurantOwnerNotFoundException(restaurantOwnerId));
    }

    private Restaurant getRestaurant(Long restaurantId) {
        return restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
    }
}