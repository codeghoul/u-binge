package com.finalassessment.ubinge.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodItemDTO extends BaseEntityDTO {
    private String name;
    private Double price;
    private Long restaurantId;
}
