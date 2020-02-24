package com.spring2020.staffwebapp.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring2020.staffwebapp.domain.dto.DbResponseDto;
import com.spring2020.staffwebapp.domain.dto.StaffProfileDto;
import com.spring2020.staffwebapp.domain.dto.UpdateStaffDto;
import com.spring2020.staffwebapp.domain.entity.AppUser;
import com.spring2020.staffwebapp.domain.entity.Staff;
import com.spring2020.staffwebapp.domain.enums.DbMessageEnum;
import com.spring2020.staffwebapp.domain.enums.DbStatusEnum;
import com.spring2020.staffwebapp.repositories.AppUserRepository;
import com.spring2020.staffwebapp.repositories.StaffRepository;
import com.spring2020.staffwebapp.services.StaffProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StaffProfileServiceImpl implements StaffProfileService
{
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    StaffRepository staffRepository;
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public StaffProfileDto viewStaffProfile(String username)
    {
        StaffProfileDto staffProfileDto = new StaffProfileDto();
        Optional<AppUser> appUser = appUserRepository.findAppUserByUsername(username);
        if (appUser.isPresent())
        {
            Optional<Staff> staff = staffRepository.findStaffByAppUserId(appUser.get().getId());
            if (staff.isPresent())
            {
                staffProfileDto.setAddress(staff.get().getAddress());
                staffProfileDto.setDob(staff.get().getDob());
                staffProfileDto.setHireDate(staff.get().getHireDate());
                staffProfileDto.setSocialId(staff.get().getSocialId());
                staffProfileDto.setStaffId(staff.get().getId());
            }
            staffProfileDto.setUsername(appUser.get().getUsername());
            staffProfileDto.setEmail(appUser.get().getEmail());
            staffProfileDto.setFirstName(appUser.get().getFirstName());
            staffProfileDto.setLastName(appUser.get().getLastName());
            staffProfileDto.setPhone(appUser.get().getPhone());
            staffProfileDto.setGender(appUser.get().getGender());
            staffProfileDto.setUserType(appUser.get().getUserType());
        }

        return staffProfileDto;
    }

    @Override
    public DbResponseDto updateStaff(UpdateStaffDto request)
    {
        /*Set return database not used*/
        DbResponseDto dbResponseDto = new DbResponseDto();
        dbResponseDto.setDbStatus(DbStatusEnum.PENDING.getCode());
        dbResponseDto.setDbMessage(DbMessageEnum.PENDING.getMessage());
        /*==============*/

        String username = request.getUsername();
        Optional<AppUser> appUser = appUserRepository.findAppUserByUsername(username);
        if (appUser.isPresent())
        {
            appUser.get().setPhone(request.getPhone());
            appUser.get().setEmail(request.getEmail());
            try
            {
                appUserRepository.save(appUser.get());
            } catch (Exception e)
            {
                /*return database failed*/
                dbResponseDto.setDbStatus(DbStatusEnum.FAILED.getCode());
                dbResponseDto.setDbMessage(DbMessageEnum.FAILED.getMessage());
                dbResponseDto.setReason(e.getMessage());
                return dbResponseDto;
            }
            Optional<Staff> staff = staffRepository.findStaffByAppUserId(appUser.get().getId());
            if (staff.isPresent())
            {
                staff.get().setAddress(request.getAddress());
                try
                {
                    staffRepository.save(staff.get());
                } catch (Exception e)
                {
                    /*return database failed*/
                    dbResponseDto.setDbStatus(DbStatusEnum.FAILED.getCode());
                    dbResponseDto.setDbMessage(DbMessageEnum.FAILED.getMessage());
                    dbResponseDto.setReason(e.getMessage());
                    return dbResponseDto;
                }
            }
        } else
        {
            dbResponseDto.setReason("Username is invalid");
            return dbResponseDto;

        }

        /*Return database update successfully*/
        dbResponseDto.setDbStatus(DbStatusEnum.SUCCESS.getCode());
        dbResponseDto.setDbMessage(DbMessageEnum.SUCCESS.getMessage());
        dbResponseDto.setReason(DbMessageEnum.SUCCESS.getMessage());
        return dbResponseDto;
    }

}
