package com.spring2020.customerapp.domain.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {

    private Long id;

    @NotNull(message = "{product.name.notNull}")
    @Length(min = 1, max = 100, message = "{product.name.length}")
    private String name;

    @NotNull(message = "{product.price.notNull}")
    private double price;

    @NotNull(message = "{product.description.notNull}")
    @Length(min = 1, max = 500, message = "{product.description.length}")
    private String description;

    private boolean isAvailable;

    @Valid
    private CategoryDto category;

    @NotNull(message = "{product.urlImg.notNull}")
    @Length(min = 1, max = 500, message = "{product.urlImg.length}")
    private String url_img;

}
