package com.finalassessment.ubinge.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class RestaurantOwnerDTO extends GeneralDetailsDTO {
    private Set<RestaurantDTO> restaurants = new HashSet<>();
}
