package com.spring2020.coffeeshop.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring2020.coffeeshop.domain.dto.StaffCreateDto;
import com.spring2020.coffeeshop.domain.dto.StaffDetailDto;
import com.spring2020.coffeeshop.domain.entity.AppRole;
import com.spring2020.coffeeshop.domain.entity.Staff;
import com.spring2020.coffeeshop.domain.enums.RoleNameEnum;
import com.spring2020.coffeeshop.domain.enums.UserTypeEnum;
import com.spring2020.coffeeshop.exception.MissingInputException;
import com.spring2020.coffeeshop.repository.AppRoleRepository;
import com.spring2020.coffeeshop.repository.StaffRepository;
import com.spring2020.coffeeshop.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private AppRoleRepository appRoleRepository;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public void createStaff(StaffCreateDto staffDto) {
        if (staffDto == null) {
            throw new MissingInputException("missing input");
        }
        Staff staff = mapper.convertValue(staffDto, Staff.class);
        staff.getAppUser().setActive(true);
        staff.getAppUser().setUserType(UserTypeEnum.STAFF);
        staff.setHireDate(LocalDate.now());
        AppRole role = appRoleRepository.findByName(RoleNameEnum.ROLE_STAFF)
                .orElse(new AppRole(1, RoleNameEnum.ROLE_STAFF));
        staff.getAppUser().setAppRole(role);
        staff.getAppUser().setPassword(encoder.encode(staff.getAppUser().getPassword()));
        staffRepository.save(staff);
    }

    @Override
    public StaffDetailDto findStaffByUsername(String username) {
        return mapper.convertValue(staffRepository.findByUserName(username), StaffDetailDto.class);
    }


}
