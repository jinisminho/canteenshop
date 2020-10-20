package com.spring2020.staffwebapp.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class StaffDto extends AuditDto
{
    private Long id;
    private LocalDate dob;
    private String address;
    private String socialId;
    private LocalDate hireDate;
    private AppUserDto appUser;

}

