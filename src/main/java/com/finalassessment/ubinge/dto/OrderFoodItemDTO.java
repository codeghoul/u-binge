package com.finalassessment.ubinge.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderFoodItemDTO extends BaseEntityDTO {
    private FoodItemDTO foodItem;
    private OrderDTO order;
    private Integer quantity;
    private Double totalPrice;
}
