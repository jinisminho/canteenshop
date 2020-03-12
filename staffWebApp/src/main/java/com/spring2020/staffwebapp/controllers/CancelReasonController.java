package com.spring2020.staffwebapp.controllers;

import com.spring2020.staffwebapp.domain.dto.CancelReasonDto;
import com.spring2020.staffwebapp.domain.dto.DbResponseDto;
import com.spring2020.staffwebapp.services.CancelReasonService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cancelReason")
public class CancelReasonController
{
    @Autowired
    CancelReasonService cancelReasonService;

    @PostMapping("/create")
    @ApiOperation(value = "Show details of an order")
    public DbResponseDto createCancelReason(@RequestParam long customerOrderId, String reason)
    {
        return cancelReasonService.createCancelReason(customerOrderId, reason);
    }

    @GetMapping("/get")
    @ApiOperation(value = "Show details of an order")
    public CancelReasonDto getCancelReason(@RequestParam long customerOrderId)
    {
        return cancelReasonService.getCancelReason(customerOrderId);
    }
}
