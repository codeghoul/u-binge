package com.finalassessment.ubinge.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class RestaurantDTO extends GeneralDetailsDTO {
    private Set<Long> foodItemIds = new HashSet<>();
    private Set<Long> orderIds = new HashSet<>();
}
