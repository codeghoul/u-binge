package com.finalassessment.ubinge.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderFoodItemVo {
    private Long foodItemId;
    private Integer quantity;
    private Double totalPrice;
}
