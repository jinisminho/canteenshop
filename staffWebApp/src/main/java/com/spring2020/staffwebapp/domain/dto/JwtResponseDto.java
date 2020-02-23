package com.spring2020.staffwebapp.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtResponseDto
{
    private String tokenPrefix;
    private String token;
    private StaffProfileDto staffProfileDto;
}
