package com.finalassessment.ubinge.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity(name = "FoodItem")
@Table(name = "food_item")
public class FoodItem extends BaseEntity {
    @Column
    private String name;

    @Column
    private Double price;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @JsonBackReference(value=  "restaurant-foodItems")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "foodItem")
    @JsonManagedReference(value = "foodItem-orderFoodItems")
    private Set<OrderFoodItem> orderFoodItems;
}
