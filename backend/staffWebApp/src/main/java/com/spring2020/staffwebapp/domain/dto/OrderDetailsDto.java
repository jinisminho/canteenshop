package com.spring2020.staffwebapp.domain.dto;

import com.spring2020.staffwebapp.domain.entity.Customer;
import com.spring2020.staffwebapp.domain.entity.OrderDetail;
import com.spring2020.staffwebapp.domain.entity.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class OrderDetailsDto extends AuditDto {
    private Long id;
    private String location;
    private String note;
    private double totalPrice;
    private List<OrderDetail> orderDetails;
    private OrderStatus status;
    private StaffDto staff;
    private CustomerDto customer;
}
