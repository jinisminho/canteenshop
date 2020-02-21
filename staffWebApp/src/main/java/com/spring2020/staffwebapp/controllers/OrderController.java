package com.spring2020.staffwebapp.controllers;

import com.spring2020.staffwebapp.domain.dto.DbResponseDto;
import com.spring2020.staffwebapp.domain.dto.OrderDetailsDto;
import com.spring2020.staffwebapp.domain.enums.OrderStatusEnum;
import com.spring2020.staffwebapp.services.OrderInfoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController
{
    @Autowired
    OrderInfoService orderInfoService;

    @GetMapping("/details")
    @ApiOperation(value = "Show details of an order")
    public OrderDetailsDto viewOrderDetails(@RequestParam(value = "Order id") long id)
    {
        return orderInfoService.viewOrderDetails(id);
    }

    @GetMapping("/searchByStaff")
    @ApiOperation(value = "Find order with staff's username")
    public Page<OrderDetailsDto> findOrdersByStaff(@RequestParam(value = "Staff username") String username
            , Pageable pageable)
    {
        return orderInfoService.findOrdersByStaff(username, pageable);
    }

    @GetMapping("/searchInPeriod")
    @ApiOperation(value = "Find order in a period")
    public Page<OrderDetailsDto> findOrdersInPeriod(
            @RequestParam(value = "From") @ApiParam(example = "2020-01-01", required = true, value = "Format YYYY-MM-dd") String from
            , @RequestParam(value = "To") @ApiParam(example = "2020-01-01", required = true, value = "Format YYYY-MM-dd") String to
            , Pageable pageable)
    {
        return orderInfoService.findOrdersInPeriod(from, to, pageable);
    }

    @GetMapping("/searchByStatus")
    @ApiOperation(value = "Get a list of order by status")
    public Page<OrderDetailsDto> viewOrderListByStatus(
            @RequestParam(value = "Status") @ApiParam(example = "Pending", required = true, value = "Status") OrderStatusEnum orderStatusEnum
            , Pageable pageable)
    {
        return orderInfoService.findOrdersByStatus(orderStatusEnum, pageable);
    }

    @PostMapping("/editStatus")
    @ApiOperation(value = "Change an order status")
    public DbResponseDto editOrderStatus(
            @RequestParam(value = "Id") @ApiParam(required = true, value = "Order id", example = "1") long id
            , @RequestParam(value = "Status") @ApiParam(example = "Pending", required = true, value = "Status") OrderStatusEnum orderStatusEnum)
    {
        return orderInfoService.editOrderStatus(id, orderStatusEnum);
    }
}
