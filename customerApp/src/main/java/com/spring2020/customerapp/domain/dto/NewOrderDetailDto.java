package com.spring2020.customerapp.domain.dto;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewOrderDetailDto {

    @NotNull(message = "{newOrderDetail.quantity.notNull}")
    private int quantity;

    @NotNull(message = "{newOrderDetail.product.notNull}")
    @Valid
    private ProductDto product;

}
