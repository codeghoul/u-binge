package com.finalassessment.ubinge.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Restaurant extends GeneralDetails {

    @ManyToOne
    @JoinColumn(name = "restaurant_owner_id")
    @JsonBackReference(value = "restaurantOwner-restaurants")
    private RestaurantOwner restaurantOwner;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "restaurant-foodItems")
    private Set<FoodItem> foodItems = new HashSet<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "restaurant-orders")
    private Set<Order> orders = new HashSet<>();
}
