package com.spring2020.staffwebapp.domain.dto;

import com.spring2020.staffwebapp.domain.enums.UserRoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AppRoleDto
{
    private Integer id;
    private UserRoleEnum name;
}
