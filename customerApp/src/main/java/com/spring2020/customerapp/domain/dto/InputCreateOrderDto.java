package com.spring2020.customerapp.domain.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InputCreateOrderDto {
    @NotNull(message = "{order.location.notNull}")
    @Length( max = 500, message = "{order.location.length}")
    private String location;

    @NotNull(message = "{order.note.notNull}")
    @Length( max = 500, message = "{order.note.length}")
    private String note;

//    @NotNull(message = "{order.totalPrice.notNull}")
//    private double totalPrice;

    @NotNull(message = "{order.orderDetails.notNull}")
    @Valid
    private List<InputNewOrderDetailDto> orderDetails;

    @NotNull(message = "{order.customerId.notNull}")
    private int customerId;
}
