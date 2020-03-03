package com.spring2020.customerapp.mapper;

import com.spring2020.customerapp.domain.dto.InputNewOrderDetailDto;
import com.spring2020.customerapp.domain.dto.NewOrderDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SubMapper {

    @Mapping(target = "product.id", source = "product")
    NewOrderDetailDto dtoToDto(InputNewOrderDetailDto dto);
}
