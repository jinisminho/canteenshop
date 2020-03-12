package com.spring2020.coffeeshop.controller;

import com.spring2020.coffeeshop.domain.dto.AppUserDto;
import com.spring2020.coffeeshop.domain.enums.UserTypeEnum;
import com.spring2020.coffeeshop.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import static com.spring2020.coffeeshop.util.ConstantUtil.UPDATE_SUCCESS;

@RestController
@RequestMapping("/appUsers")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/{id}")
    public AppUserDto findAppUserById(@PathVariable(value = "id") long id) {
        return appUserService.findAppUserById(id);
    }

    @GetMapping
    public Page<AppUserDto> findAppUser(@RequestParam(value = "userType") UserTypeEnum userType,
                                        @RequestParam(required = false, value = "name") String name,
                                        Pageable pageable) {
        if (name != null) {
            return appUserService.findAppUserByName(name, userType, pageable);
        }
        //find base on user type
        return appUserService.findAllAppUsers(userType, pageable);
    }

    @GetMapping("/active/{id}")
    public String updateAppUserStatus(@PathVariable(value = "id") long id,
                                      @RequestParam(value = "userType") UserTypeEnum userType,
                                      @RequestParam(value = "isActive") boolean isActive) {
        System.out.println("isActive");
        appUserService.updateAppUserStatus(userType, id, isActive);
        return UPDATE_SUCCESS;
    }

}
