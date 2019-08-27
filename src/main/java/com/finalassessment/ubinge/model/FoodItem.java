package com.finalassessment.ubinge.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "FoodItem")
@Table(name = "food_item", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class FoodItem extends BaseEntity {
    @Column
    private String name;

    @Column
    private Double price;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonBackReference(value = "restaurant-foodItems")
    private Restaurant restaurant;
}
