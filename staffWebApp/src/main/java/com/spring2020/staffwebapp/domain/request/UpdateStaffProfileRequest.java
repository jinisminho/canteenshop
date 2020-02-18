package com.spring2020.staffwebapp.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class UpdateStaffProfileRequest
{
    @NotNull(message = "{userApp.username.notNull}")
    @Length(min = 6, max = 50, message = "{userApp.username.length}")
    private String username;
    private String address;
    @NumberFormat
    @Length(min = 10, max = 10)
    private String phone;
    @Email(message = "{userApp.email.pattern}")
    private String email;
}
