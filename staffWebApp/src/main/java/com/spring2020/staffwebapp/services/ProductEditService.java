package com.spring2020.staffwebapp.service;

import com.spring2020.staffwebapp.domain.dto.DbResponseDto;

public interface ProductEditService
{
    DbResponseDto editProductAvailability(long id, boolean isAvailable);
}
