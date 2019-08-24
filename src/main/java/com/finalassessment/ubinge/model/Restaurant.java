package com.finalassessment.ubinge.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "restaurant")
public class Restaurant extends GeneralDetails {

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_owner_id")
    @JsonBackReference(value = "restaurantOwner-restaurants")
    private RestaurantOwner restaurantOwner;

    @OneToMany(mappedBy = "restaurant")
    @JsonManagedReference(value=  "restaurant-foodItems")
    private Set<FoodItem> foodItems;

    @OneToMany(mappedBy = "restaurant")
    @JsonManagedReference(value = "restaurant-orders")
    private Set<Order> orders;
}
