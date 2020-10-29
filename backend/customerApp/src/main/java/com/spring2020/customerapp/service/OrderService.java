package com.spring2020.customerapp.service;

import com.spring2020.customerapp.domain.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(InputCreateOrderDto orderDto);

    void cancelOrder(Long id);

    OrderDetailDto viewOrderDetail(Long id);

    List<OrderDto> viewOrderHistory(long customerId);


    String cancelOrder(CancelOrderDto dto);
}
