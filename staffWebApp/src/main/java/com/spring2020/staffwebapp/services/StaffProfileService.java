package com.spring2020.staffwebapp.services;

import com.spring2020.staffwebapp.domain.dto.DbResponseDto;
import com.spring2020.staffwebapp.domain.dto.StaffProfileDto;
import com.spring2020.staffwebapp.domain.dto.UpdateStaffDto;

public interface StaffProfileService
{

    StaffProfileDto viewStaffProfile(String username);

    DbResponseDto updateStaff(UpdateStaffDto request);
}
