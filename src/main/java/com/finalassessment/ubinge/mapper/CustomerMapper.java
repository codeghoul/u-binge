package com.finalassessment.ubinge.mapper;

import com.finalassessment.ubinge.dto.CustomerDTO;
import com.finalassessment.ubinge.dto.OrderDTO;
import com.finalassessment.ubinge.model.Customer;
import com.finalassessment.ubinge.model.Order;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CustomerMapper {
    CustomerMapper CUSTOMER_MAPPER = Mappers.getMapper(CustomerMapper.class);

    Customer toEntity(CustomerDTO customerDTO, @Context CustomerContext context);

    @Mapping(target = "customer", ignore = true)
    Order toEntity(OrderDTO orderDTO, @Context CustomerContext context);
}
