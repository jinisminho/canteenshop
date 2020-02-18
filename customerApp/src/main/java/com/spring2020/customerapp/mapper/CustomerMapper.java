package com.spring2020.customerapp.mapper;

import com.spring2020.customerapp.domain.dto.*;
import com.spring2020.customerapp.domain.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper( CustomerMapper.class );

    Customer toEntity(CustomerDto customer);

    Customer toEntity(CreateCustomerDto customer);

    Customer toEntity(UpdateCustomerDto customer);

    CustomerDto toDto(Customer customer);

    AppUserDto toAppUserDto(UpdateAppUserDto dto);

    UpdateCustomerDto toUpdateDto(Customer customer);

    CreateAppUserDto toCrAppUserDto(CreateAppUserDto dto);

}
