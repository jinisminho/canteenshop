package com.spring2020.staffwebapp.domain.dto;

import com.spring2020.staffwebapp.domain.enums.GenderEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class StaffProfileDto
{
    @NotNull(message = "{userApp.username.notNull}")
    @Length(min = 6, max = 50, message = "{userApp.username.length}")
    private String username;
    private String firstName;
    private String lastName;
    @NumberFormat
    @Length(min = 10, max = 10)
    private String phone;
    @Email(message = "{userApp.email.pattern}")
    private String email;
    private GenderEnum gender;
    private LocalDate dob;
    private String address;
    private String socialId;
    private LocalDate hireDate;
    private Long staffId;
    private String userType;
}
