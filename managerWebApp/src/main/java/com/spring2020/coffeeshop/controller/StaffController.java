package com.spring2020.coffeeshop.controller;

import com.spring2020.coffeeshop.domain.dto.StaffCreateDto;
import com.spring2020.coffeeshop.domain.dto.StaffDetailDto;
import com.spring2020.coffeeshop.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.spring2020.coffeeshop.util.ConstantUtil.CREATE_SUCCESS;

@RestController
@RequestMapping("/api/staffs")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping
    public String createStaff(@Valid @RequestBody StaffCreateDto staffDto) {
        staffService.createStaff(staffDto);
        return CREATE_SUCCESS;
    }

    @GetMapping
    public StaffDetailDto findStaffByUsername(@RequestParam("username") String username) {
        return staffService.findStaffByUsername(username);
    }
}
