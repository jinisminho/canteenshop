package com.spring2020.customerapp.service;

import com.spring2020.customerapp.domain.dto.CreateAppUserDto;
import com.spring2020.customerapp.domain.dto.CustomerDto;
import com.spring2020.customerapp.domain.dto.UpdateAppUserDto;
import com.spring2020.customerapp.domain.dto.UserInfoDto;

public interface CustomerService {
    void updateCustomer(Long appUserId, UpdateAppUserDto dto);

    CustomerDto createCustomer(CreateAppUserDto dto);

    CustomerDto getCustomer(int id);

    UserInfoDto getCustomerByAppUserId(Long id);
}
