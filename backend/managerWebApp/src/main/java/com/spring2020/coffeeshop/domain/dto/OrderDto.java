package com.spring2020.coffeeshop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto extends AuditDto {

    private Long id;

    private String location;

    private String note;

    private double totalPrice;

    private List<OrderDetailDto> orderDetails;

    private OrderStatusDto status;

    private OrderStaffDto staff;
}
