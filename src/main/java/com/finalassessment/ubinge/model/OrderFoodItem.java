package com.finalassessment.ubinge.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "order_food_item")
public class OrderFoodItem extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_item_id")
//    @JsonBackReference(value = "foodItem-orderFoodItems")
    private FoodItem foodItem;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonBackReference(value = "order-orderFoodItems")
    private Order order;


    private Integer quantity;
    private Double totalPrice;
}
