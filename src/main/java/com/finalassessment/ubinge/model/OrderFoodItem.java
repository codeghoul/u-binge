package com.finalassessment.ubinge.model;

public class OrderFoodItem extends BaseEntity {
    private FoodItem foodItem;
    private Order order;
    private Integer quantity;
    private Double totalPrice;
}
