<<<<<<< HEAD
package com.spring2020.staffwebapp.controllers;
=======
package com.spring2020.staffwebapp.controller;
>>>>>>> hoangpm14

import com.spring2020.staffwebapp.domain.dto.DbResponseDto;
import com.spring2020.staffwebapp.domain.entity.AppUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appUser")
public class AppUserController
{
    @PostMapping("/createUser")
    public DbResponseDto createUser(AppUser appUser)
    {
        return null;
    }
}
