package com.spring2020.customerapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

import static com.spring2020.customerapp.util.ConstantUtil.DATE_TIME_PATTERN;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StaffDto {

    private Long id;

    @NotNull(message = "{staff.dob.notNull}")
    @Past(message = "{staff.dob.past}")
    private LocalDate dob;

    @NotNull(message = "{staff.address.notNull}")
    @Length(min = 1, max = 500, message = "{staff.address.length}")
    private String address;

    @NotNull(message = "{staff.socialId.notNull}")
    @Length(min = 12, max = 12, message = "{staff.socialId.length}")
    private String socialId;

    @DateTimeFormat(pattern = DATE_TIME_PATTERN)
    private LocalDate hireDate;

    @DateTimeFormat(pattern = DATE_TIME_PATTERN)
    private LocalDate terminateDate;

    @Valid
    @NotNull
    private StaffAppUserDto appUser;
}
