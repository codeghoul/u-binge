package com.finalassessment.ubinge.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderFoodItemDTO extends BaseEntityDTO {
    private Long foodItemId;
    private Long orderId;
    private Integer quantity;
    private Double totalPrice;
}
