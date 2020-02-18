package com.spring2020.staffwebapp.services;

import com.spring2020.staffwebapp.domain.dto.DbResponseDto;
import com.spring2020.staffwebapp.domain.dto.OrderCheckoutDto;

public interface OrderCheckoutService
{
    DbResponseDto checkoutOrderStaff(OrderCheckoutDto request);
}
