package com.finalassessment.ubinge.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO extends GeneralDetailsDTO {
    Set<Long> orderIds = new HashSet<>();
}
