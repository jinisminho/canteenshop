package com.spring2020.staffwebapp.services;

import com.spring2020.staffwebapp.domain.dto.CancelReasonDto;
import com.spring2020.staffwebapp.domain.dto.DbResponseDto;

public interface CancelReasonService
{
    DbResponseDto createCancelReason(Long customerOrderId, String reason);

    CancelReasonDto getCancelReason(Long customerOrderId);
}
