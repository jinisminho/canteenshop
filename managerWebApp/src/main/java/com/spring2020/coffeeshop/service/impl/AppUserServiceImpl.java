package com.spring2020.coffeeshop.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring2020.coffeeshop.domain.dto.AppUserDto;
import com.spring2020.coffeeshop.domain.entity.AppUser;
import com.spring2020.coffeeshop.domain.entity.Staff;
import com.spring2020.coffeeshop.domain.enums.UserTypeEnum;
import com.spring2020.coffeeshop.exception.ResourceNotFoundException;
import com.spring2020.coffeeshop.repository.AppUserRepository;
import com.spring2020.coffeeshop.repository.StaffRepository;
import com.spring2020.coffeeshop.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public AppUserDto findAppUserById(long id) {
        return mapper.convertValue(findAppUserByIdReturnAppUser(id), AppUserDto.class);

    }

    @Override
    public Page<AppUserDto> findAppUserByName(String name, UserTypeEnum userType, Pageable pageable) {
        return appUserRepository.findByNameAndUserType(name, userType.toString(), pageable)
                .map(appUser -> mapper.convertValue(appUser, AppUserDto.class));
    }

    @Override
    public Page<AppUserDto> findAllAppUsers(UserTypeEnum userType, Pageable pageable) {
        return appUserRepository.findByUserType(userType, pageable)
                .map(appUser -> mapper.convertValue(appUser, AppUserDto.class));
    }


    @Override
    @Transactional
    public void updateAppUserStatus(UserTypeEnum userType, long id, boolean status) {
        AppUser appUser = findAppUserByIdReturnAppUser(id);
        appUser.setActive(status);
        appUserRepository.save(appUser);
        if (userType == UserTypeEnum.STAFF) {
            Staff staff = staffRepository.findByAppUserId(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Staff not found"));
            if (status) {
                staff.setTerminateDate(null);
            } else {
                staff.setTerminateDate(LocalDate.now());
            }
            staffRepository.save(staff);
        }

    }

    private AppUser findAppUserByIdReturnAppUser(long id) {
        return appUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
    }
}
