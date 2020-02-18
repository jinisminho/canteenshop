package com.spring2020.customerapp.mapper;

import com.spring2020.customerapp.domain.dto.CreateOrderDto;
import com.spring2020.customerapp.domain.dto.OrderDetailDto;
import com.spring2020.customerapp.domain.dto.OrderDto;
import com.spring2020.customerapp.domain.entity.CustomerOrder;
import com.spring2020.customerapp.domain.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper( OrderMapper.class );

    OrderDetailDto toDetailDto(OrderDetail order);

    OrderDetail toEntity(OrderDetailDto order);

    OrderDto toOrderDto(CustomerOrder order);

    CustomerOrder toEntity(OrderDto order);

    @Mapping(target = "customerId", source = "customer.id")
    CreateOrderDto toCreateOrderDto(CustomerOrder order);

    @Mapping(target = "customer.id", source = "customerId")
    CustomerOrder toEntity(CreateOrderDto order);



}
