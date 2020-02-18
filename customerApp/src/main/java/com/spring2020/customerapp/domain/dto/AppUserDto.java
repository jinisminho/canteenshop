package com.spring2020.customerapp.domain.dto;

import com.spring2020.customerapp.domain.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.spring2020.customerapp.util.ConstantUtil.PHONE_REX;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDto {

    private Long id;

    @NotNull(message = "{userApp.username.notNull}")
    @Length(min = 6, max = 50, message = "{userApp.username.length}")
    private String username;


    @NotNull(message = "{userApp.firstName.notNull}")
    @Length(min = 1, max = 100, message = "{userApp.firstName.length}")
    private String firstName;

    @NotNull(message = "{userApp.lastName.notNull}")
    @Length(min = 1, max = 50, message = "{userApp.lastName.length}")
    private String lastName;

    @NotNull(message = "{userApp.phone.notNull}")
    @Pattern(regexp = PHONE_REX, message = "{userApp.phone.pattern}")
    private String phone;

    @NotNull(message = "{userApp.email.notNull}")
    @Email(message = "{userApp.email.pattern}")
    private String email;

    @NotNull(message = "{userApp.gender.notNull}")
    private GenderEnum gender;

    private boolean isActive;

}
