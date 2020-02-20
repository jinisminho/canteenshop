package com.spring2020.staffwebapp.controllers;

import com.spring2020.staffwebapp.domain.dto.DbResponseDto;
import com.spring2020.staffwebapp.domain.dto.OrderCheckoutDto;
import com.spring2020.staffwebapp.domain.dto.StaffProfileDto;
import com.spring2020.staffwebapp.services.OrderCheckoutService;
import com.spring2020.staffwebapp.services.StaffProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/staffs")
public class StaffController
{
    @Autowired
    private StaffProfileService staffProfileService;
    @Autowired
    private OrderCheckoutService orderCheckoutService;

    @GetMapping("/profile")
    public StaffProfileDto viewStaffProfile(@RequestParam(value = "Username") String username)
    {
        return staffProfileService.viewStaffProfile(username);
    }

    @PostMapping("/profile")
    public DbResponseDto updateStaffProfile(@RequestBody @Valid StaffProfileDto request)
    {
        return staffProfileService.updateStaff(request);
    }

    @PostMapping("/checkout")
    public DbResponseDto checkoutOrderStaff(@RequestBody @Valid OrderCheckoutDto request)
    {
        return orderCheckoutService.checkoutOrderStaff(request);
    }
}
