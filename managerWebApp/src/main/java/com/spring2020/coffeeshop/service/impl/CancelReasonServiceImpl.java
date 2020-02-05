package com.spring2020.coffeeshop.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring2020.coffeeshop.domain.dto.CancelReasonDto;
import com.spring2020.coffeeshop.domain.entity.CancelReason;
import com.spring2020.coffeeshop.exception.ResourceNotFoundException;
import com.spring2020.coffeeshop.repository.CancelReasonRepository;
import com.spring2020.coffeeshop.service.CancelReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancelReasonServiceImpl implements CancelReasonService {

    @Autowired
    private CancelReasonRepository reasonRepository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public CancelReasonDto findCancelReasonByOrderId(long orderId) {
        CancelReason cancelReason = reasonRepository.findByOrderId(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order with id " + orderId + " not found"));
        return mapper.convertValue(cancelReason, CancelReasonDto.class);
    }
}
