package com.spring2020.customerapp.controller;

import com.spring2020.customerapp.domain.dto.CancelOrderDto;
import com.spring2020.customerapp.domain.dto.InputCreateOrderDto;
import com.spring2020.customerapp.domain.dto.OrderDetailDto;
import com.spring2020.customerapp.domain.dto.OrderDto;
import com.spring2020.customerapp.service.OrderService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public OrderDto createOrder(@RequestBody @Valid InputCreateOrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }

    ;

    @PostMapping("/cancel")
    public String cancelOrder(@RequestBody @Valid CancelOrderDto cancelOrderDto) {
        return orderService.cancelOrder(cancelOrderDto);
    }

    ;

    @GetMapping("/{id}/detail")
    public OrderDetailDto viewOrderDetail(@PathVariable("id") Long id) {
        return orderService.viewOrderDetail(id);
    }

    ;

    @GetMapping("/{id}/history")
    public List<OrderDto> viewOrderHistory(@PathVariable("id") @ApiParam(value = "AppUser Id") long appUserId) {
        return orderService.viewOrderHistory(appUserId);
    }

    ;
}
