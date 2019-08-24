package com.finalassessment.ubinge.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "restaurant_owner")
public class RestaurantOwner extends GeneralDetails {
    @OneToMany(mappedBy = "restaurantOwner")
    private Set<Restaurant> restaurants = new HashSet<>();
}
