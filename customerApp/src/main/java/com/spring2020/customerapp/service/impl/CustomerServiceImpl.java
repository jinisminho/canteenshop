package com.spring2020.customerapp.service.impl;

import com.spring2020.customerapp.domain.dto.*;
import com.spring2020.customerapp.domain.entity.AppUser;
import com.spring2020.customerapp.domain.entity.Customer;
import com.spring2020.customerapp.domain.enums.AppRoleEnum;
import com.spring2020.customerapp.domain.enums.UserTypeEnum;
import com.spring2020.customerapp.exception.CommonException;
import com.spring2020.customerapp.exception.MissingInputException;
import com.spring2020.customerapp.mapper.AppUserMapper;
import com.spring2020.customerapp.mapper.CustomerMapper;
import com.spring2020.customerapp.repository.AppUserRepository;
import com.spring2020.customerapp.repository.CustomerRepository;
import com.spring2020.customerapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public void updateCustomer(Long appUserId, UpdateAppUserDto dto) {

        Customer customer = customerRepository.findByAppUser_Id(appUserId).orElse(null);

        if (customer == null) {
            throw new CommonException("Can't find Customer with id [" + appUserId + "]");
        }
        if (dto == null) {
            throw new MissingInputException("missing input");
        }

        UpdateCustomerDto customerDto = CustomerMapper.INSTANCE.toUpdateDto(customer);
        customerDto.getAppUser().setFirstName(dto.getFirstName());
        customerDto.getAppUser().setLastName(dto.getLastName());
        customerDto.getAppUser().setEmail(dto.getEmail());
        customerDto.getAppUser().setPhone(dto.getPhone());

        customer = CustomerMapper.INSTANCE.toEntity(customerDto);
        appUserRepository.saveAndFlush(customer.getAppUser());
        customerRepository.saveAndFlush(customer);

    }

    @Override
    public CustomerDto createCustomer(CreateAppUserDto appUserDto) {
        if (appUserDto == null) {
            throw new MissingInputException("missing input");
        }
        CreateCustomerDto dto = new CreateCustomerDto();
        dto.setAppUser(CustomerMapper.INSTANCE.toCrAppUserDto(appUserDto));
        Customer customer = CustomerMapper.INSTANCE.toEntity(dto);
        customer.getAppUser().setActive(true);
        customer.getAppUser().setUserType(UserTypeEnum.CUSTOMER);
        customer.getAppUser().setAppRole(AppRoleEnum.ROLE_CUSTOMER.getAppRole());
        customer.getAppUser().setPassword(encoder.encode(customer.getAppUser().getPassword()));
        appUserRepository.save(customer.getAppUser());
        CustomerDto saved = CustomerMapper.INSTANCE.toDto(customerRepository.saveAndFlush(customer));

        return saved;
    }

    @Override
    public CustomerDto getCustomer(int id) {
        return CustomerMapper.INSTANCE.toDto(customerRepository.findById(id).get());
    }

    @Override
    public UserInfoDto getCustomerByAppUserId(Long id) {
        UserInfoDto result = new UserInfoDto();
        try {
            result = AppUserMapper.INSTANCE.toDto(appUserRepository.findById(id).get());
        } catch (Exception e) {
            throw new CommonException("App User Id not Found");
        }

        try {
            customerRepository.findByAppUser_Id(id).get();
        } catch (Exception e) {
            throw new CommonException("Customer with AppUserId " + id + " not Found");
        }

        return result;
    }
}
