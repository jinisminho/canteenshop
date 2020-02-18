package com.spring2020.customerapp.domain.dto;

import com.spring2020.customerapp.domain.enums.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusDto {

    private Integer id;

    private OrderStatusEnum status;
}
