package com.spring2020.staffwebapp.services.impl;

import com.spring2020.staffwebapp.domain.dto.DbResponseDto;
import com.spring2020.staffwebapp.domain.entity.AppUser;
import com.spring2020.staffwebapp.domain.enums.DbMessageEnum;
import com.spring2020.staffwebapp.domain.enums.DbStatusEnum;
import com.spring2020.staffwebapp.repositories.AppUserRepository;
import com.spring2020.staffwebapp.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService
{
    @Autowired
    AppUserRepository applicationUserRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public DbResponseDto createUser(AppUser appUser)
    {
        /*Set return database not used*/
        DbResponseDto dbResponseDto = new DbResponseDto();
        dbResponseDto.setDbStatus(DbStatusEnum.PENDING.getCode());
        dbResponseDto.setDbMessage(DbMessageEnum.PENDING.getMessage());
        dbResponseDto.setReason(DbMessageEnum.PENDING.getMessage());
        /*===========================*/

        /*Encrypt user's password*/
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        /*=======================*/
        try
        {
            applicationUserRepository.save(appUser);
            dbResponseDto.setDbStatus(DbStatusEnum.SUCCESS.getCode());
            dbResponseDto.setDbMessage(DbMessageEnum.SUCCESS.getMessage());
            dbResponseDto.setReason(DbMessageEnum.SUCCESS.getMessage());
        } catch (Exception e)
        {
            dbResponseDto.setDbStatus(DbStatusEnum.FAILED.getCode());
            dbResponseDto.setDbMessage(DbMessageEnum.FAILED.getMessage());
            dbResponseDto.setReason(e.getMessage());
        }
        return dbResponseDto;
    }
}
