package com.spring2020.coffeeshop.service;


import com.spring2020.coffeeshop.domain.dto.CancelReasonDto;

public interface CancelReasonService {


    CancelReasonDto findCancelReasonByOrderId(long orderId);
}
