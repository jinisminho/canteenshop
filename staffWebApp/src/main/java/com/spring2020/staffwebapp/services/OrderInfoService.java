package com.spring2020.staffwebapp.services;

import com.spring2020.staffwebapp.domain.dto.DbResponseDto;
import com.spring2020.staffwebapp.domain.dto.OrderDetailsDto;
import com.spring2020.staffwebapp.domain.enums.OrderStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderInfoService
{
    OrderDetailsDto viewOrderDetails(Long id);

    Page<OrderDetailsDto> findOrdersByStaff(String username, Pageable pageable);

    Page<OrderDetailsDto> findOrdersInPeriod(String from, String to
            , Pageable pageable);

    Page<OrderDetailsDto> findOrdersByStatus(OrderStatusEnum orderStatusEnum, Pageable pageable);

    DbResponseDto editOrderStatus(Long id, OrderStatusEnum orderStatusEnum);

    Page<OrderDetailsDto> findOrderInPeriodByStatus(OrderStatusEnum orderStatusEnum, String from, String to, Pageable pageable);
}
