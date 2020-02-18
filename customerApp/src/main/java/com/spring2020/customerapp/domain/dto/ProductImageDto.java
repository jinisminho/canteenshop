package com.spring2020.customerapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageDto {


    private Long id;

    @Length(min = 1, max = 500, message = "{productImage.imgUrl.length}")
    private String imgUrl;
}
