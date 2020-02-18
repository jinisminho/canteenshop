package com.spring2020.customerapp.service;

import com.spring2020.customerapp.domain.dto.CreateAppUserDto;
import com.spring2020.customerapp.domain.dto.CustomerDto;
import com.spring2020.customerapp.domain.dto.UpdateAppUserDto;

public interface CustomerService {
    void updateCustomer(int id, UpdateAppUserDto dto);

    CustomerDto createCustomer(CreateAppUserDto dto);

    CustomerDto getCustomer(int id);
}
