package com.spring2020.customerapp.service;

import com.spring2020.customerapp.domain.dto.CreateOrderDto;
import com.spring2020.customerapp.domain.dto.InputCreateOrderDto;
import com.spring2020.customerapp.domain.dto.OrderDetailDto;
import com.spring2020.customerapp.domain.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderDto createOrder(InputCreateOrderDto orderDto);

    void cancelOrder(Long id);

    OrderDetailDto viewOrderDetail(Long id);

    Page<OrderDto> viewOrderHistory(Pageable pageable, int customerId);

}
