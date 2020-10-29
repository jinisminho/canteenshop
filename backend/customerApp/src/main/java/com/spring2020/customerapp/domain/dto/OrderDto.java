package com.spring2020.customerapp.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto  extends  AuditDTO{

    private Long id;

    private String location;

    private String note;

    private double totalPrice;

    private List<OrderDetailDto> orderDetails;

    private OrderStatusDto status;
    @JsonIgnore
    private StaffDto staff;
    @JsonIgnore
    private CustomerDto customer;
}
