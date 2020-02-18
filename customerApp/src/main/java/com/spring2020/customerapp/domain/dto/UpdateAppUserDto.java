package com.spring2020.customerapp.domain.dto;

import com.spring2020.customerapp.domain.enums.GenderEnum;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.spring2020.customerapp.util.ConstantUtil.PHONE_REX;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateAppUserDto {

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

}
