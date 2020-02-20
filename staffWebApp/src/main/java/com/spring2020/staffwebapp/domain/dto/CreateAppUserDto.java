package com.spring2020.staffwebapp.domain.dto;

import com.spring2020.staffwebapp.domain.enums.GenderEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CreateAppUserDto
{
    private Long id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private GenderEnum gender;

    private boolean isActive;

    private String userType;
}
