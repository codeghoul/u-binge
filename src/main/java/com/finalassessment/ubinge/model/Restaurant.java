package com.finalassessment.ubinge.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "restaurant")
public class Restaurant extends GeneralDetails {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_owner_id")
    @JsonBackReference
    private RestaurantOwner restaurantOwner;

    @OneToMany(mappedBy = "restaurant")
    private Set<FoodItem> foodItems;

    @OneToMany(mappedBy = "restaurant")
    private Set<Order> orders;
}
