package com.spring2020.customerapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Integer id;

    @NotNull(message = "{category.name.notNull}")
    @Length(min = 1, max = 100, message = "{category.name.length}")
    private String name;
}
