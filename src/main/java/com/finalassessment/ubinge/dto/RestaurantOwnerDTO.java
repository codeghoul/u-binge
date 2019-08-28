package com.finalassessment.ubinge.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestaurantOwnerDTO extends GeneralDetailsDTO {
    Set<Long> restaurantIds = new HashSet<>();
}
