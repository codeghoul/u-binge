package com.finalassessment.ubinge.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "restaurant_owner", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class RestaurantOwner extends GeneralDetails {
    @OneToMany(mappedBy = "restaurantOwner", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "restaurantOwner-restaurants")
    private Set<Restaurant> restaurants = new HashSet<>();
}
