package com.spring2020.staffwebapp.services;

import com.spring2020.staffwebapp.domain.dto.DbResponseDto;
import com.spring2020.staffwebapp.domain.entity.AppUser;

public interface RegistrationService
{
    DbResponseDto createUser(AppUser appUser);

}
