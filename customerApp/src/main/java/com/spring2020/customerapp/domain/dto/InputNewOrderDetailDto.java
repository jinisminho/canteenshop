package com.spring2020.customerapp.domain.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InputNewOrderDetailDto {
    @NotNull(message = "{inputNewOrderDetail.quantity.notNull}")
    private int quantity;

    @NotNull(message = "{inputNewOrderDetail.product.notNull}")
    private Long product;
}
