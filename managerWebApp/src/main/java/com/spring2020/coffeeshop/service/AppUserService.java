package com.spring2020.coffeeshop.service;

import com.spring2020.coffeeshop.domain.dto.AppUserDto;
import com.spring2020.coffeeshop.domain.enums.UserTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AppUserService {

    AppUserDto findAppUserById(long id);

    Page<AppUserDto> findAppUserByName(String name, UserTypeEnum userType, Pageable pageable);

    Page<AppUserDto> findAllAppUsers(UserTypeEnum userType, Pageable pageable);

    void updateAppUserStatus(UserTypeEnum userType, long id, boolean status);
}
