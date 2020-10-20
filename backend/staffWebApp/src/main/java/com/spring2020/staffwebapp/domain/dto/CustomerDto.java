package com.spring2020.staffwebapp.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CustomerDto extends AuditDto
{
    private Long id;
    private AppUserDto appUser;
}
