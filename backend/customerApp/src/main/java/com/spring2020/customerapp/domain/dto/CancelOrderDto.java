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
public class CancelOrderDto {

    private Long orderId;

    @NotNull(message = "{order.orderReason.notNull}")
    @Length( max = 500, message = "{order.orderReason.length}")
    private String reason;

}
