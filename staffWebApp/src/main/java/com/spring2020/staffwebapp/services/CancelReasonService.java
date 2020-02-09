package com.spring2020.staffwebapp.service;

import com.spring2020.staffwebapp.domain.dto.CancelReasonDto;
import com.spring2020.staffwebapp.domain.dto.DbResponseDto;

import java.util.Optional;

public interface CancelReasonService
{
    DbResponseDto createCancelReason(Long customerOrderId, String reason);

    CancelReasonDto getCancelReason(Long customerOrderId);
}
