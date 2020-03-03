package com.spring2020.customerapp.controller;

import com.spring2020.customerapp.domain.dto.CreateOrderDto;
import com.spring2020.customerapp.domain.dto.InputCreateOrderDto;
import com.spring2020.customerapp.domain.dto.OrderDetailDto;
import com.spring2020.customerapp.domain.dto.OrderDto;
import com.spring2020.customerapp.service.OrderService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public OrderDto createOrder(@RequestBody @Valid InputCreateOrderDto orderDto) {
        return orderService.createOrder(orderDto);
    };

    @PutMapping("/{id}/cancel")
    public void cancelOrder(@PathVariable("id") Long id) {
        orderService.cancelOrder(id);
    };

    @GetMapping("/{id}/detail")
    public OrderDetailDto viewOrderDetail(@PathVariable("id") Long id) {
        return orderService.viewOrderDetail(id);
    };

    @GetMapping("/{id}/history")
    public Page<OrderDto> viewOrderHistory(Pageable pageable, @PathVariable("id") @ApiParam(value = "Customer Id") int customerId) {
        return orderService.viewOrderHistory(pageable, customerId);
    };
}
