package com.spring2020.customerapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppRoleDto {

    private Integer id;

    @NotNull(message = "{appRole.name.notNull}")
    @Length(max = 50, message = "{appRole.name.length}")
    private String name;

}
