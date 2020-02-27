package com.spring2020.staffwebapp.domain.dto;

import com.spring2020.staffwebapp.domain.entity.OrderStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class OrderCheckoutDto
{
    @Length(max = 500, message = "{text.sizeNotOver500}")
    @NotNull(message = "{field.notNull}")
    @ApiModelProperty(required = true)
    private String location;
    @Length(max = 500, message = "{text.sizeNotOver500}")
    private String note;
    private OrderStatus status;
    private StaffCartDto cart;
}
