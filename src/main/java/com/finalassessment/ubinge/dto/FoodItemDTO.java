package com.finalassessment.ubinge.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class FoodItemDTO extends BaseEntityDTO {

    @NotNull(message = "Food Item Name cannot be null.")
    private String name;

    @Positive
    private Double price;
    private Long restaurantId;
}
