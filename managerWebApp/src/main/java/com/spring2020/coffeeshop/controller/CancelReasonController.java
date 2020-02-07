package com.spring2020.coffeeshop.controller;

import com.spring2020.coffeeshop.domain.dto.CancelReasonDto;
import com.spring2020.coffeeshop.service.CancelReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cancelReasons")
public class CancelReasonController {

    @Autowired
    private CancelReasonService cancelReasonService;

    @GetMapping
    public CancelReasonDto findCancelReasonByOrderId(@RequestParam(value = "orderId") long orderId) {
        return cancelReasonService.findCancelReasonByOrderId(orderId);
    }


}
