package com.spring2020.staffwebapp.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;

@Getter
@Setter
@NoArgsConstructor
public class UpdateStaffDto
{
    @Length(min = 6, max = 50, message = "{userApp.username.length}")
    private String username;
    @NumberFormat
    @Length(min = 10, max = 10)
    private String phone;
    @Email(message = "{userApp.email.pattern}")
    private String email;
    private String address;
}
