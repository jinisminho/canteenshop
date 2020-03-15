package com.spring2020.customerapp.controller;

import com.spring2020.customerapp.domain.dto.CreateAppUserDto;
import com.spring2020.customerapp.domain.dto.CustomerDto;
import com.spring2020.customerapp.domain.dto.UpdateAppUserDto;
import com.spring2020.customerapp.domain.dto.UserInfoDto;
import com.spring2020.customerapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PutMapping("/{id}/update")
    public void updateCustomer(@PathVariable("id") Long appUserId, @RequestBody @Valid UpdateAppUserDto dto) {
        customerService.updateCustomer(appUserId, dto);
    };

    @PostMapping("/create")
    public CustomerDto createCustomer(@RequestBody @Valid CreateAppUserDto dto) {
        return customerService.createCustomer(dto);
    };

//    @GetMapping("/{id}")
//    public CustomerDto getCustomer(@PathVariable int id) {
//        return customerService.getCustomer(id);
//    }

    @GetMapping("/{appuserid}")
    public UserInfoDto getCustomerByAppUserId(@PathVariable Long appuserid) {
        return customerService.getCustomerByAppUserId(appuserid);
    }

}
