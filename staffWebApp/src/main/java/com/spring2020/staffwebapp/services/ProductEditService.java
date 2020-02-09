package com.spring2020.staffwebapp.services;

import com.spring2020.staffwebapp.domain.dto.DbResponseDto;

public interface ProductEditService
{
    DbResponseDto editProductAvailability(long id, boolean isAvailable);
}
