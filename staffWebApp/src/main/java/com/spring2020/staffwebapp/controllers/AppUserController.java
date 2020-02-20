package com.spring2020.staffwebapp.controllers;

import com.spring2020.staffwebapp.domain.dto.DbResponseDto;
import com.spring2020.staffwebapp.domain.entity.AppUser;
import com.spring2020.staffwebapp.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appUser")
public class AppUserController
{
    @Autowired
    RegistrationService registrationService;

    @PostMapping("/createUser")
    public DbResponseDto createUser(AppUser appUser)
    {
        return registrationService.createUser(appUser);
    }
}
