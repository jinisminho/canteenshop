package com.spring2020.staffwebapp.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DbResponseDto
{
    private String dbStatus;
    private String dbMessage;
    private String reason;
}
