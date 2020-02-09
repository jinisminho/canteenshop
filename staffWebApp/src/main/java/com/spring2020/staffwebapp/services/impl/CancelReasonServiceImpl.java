package com.spring2020.staffwebapp.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring2020.staffwebapp.domain.dto.CancelReasonDto;
import com.spring2020.staffwebapp.domain.dto.DbResponseDto;
import com.spring2020.staffwebapp.domain.entity.CancelReason;
import com.spring2020.staffwebapp.domain.entity.CustomerOrder;
import com.spring2020.staffwebapp.domain.enums.DbMessageEnum;
import com.spring2020.staffwebapp.domain.enums.DbStatusEnum;
import com.spring2020.staffwebapp.domain.enums.InputValidateMessageEnum;
import com.spring2020.staffwebapp.domain.enums.OrderStatusEnum;
import com.spring2020.staffwebapp.repository.CancelReasonRepository;
import com.spring2020.staffwebapp.repository.CustomerOrderRepository;
import com.spring2020.staffwebapp.service.CancelReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CancelReasonServiceImpl implements CancelReasonService
{
    @Autowired
    CancelReasonRepository cancelReasonRepository;
    @Autowired
    CustomerOrderRepository customerOrderRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public DbResponseDto createCancelReason(Long customerOrderId, String reason)
    {
        /*Set return database not used by default*/
        DbResponseDto dbResponseDto = new DbResponseDto();
        dbResponseDto.setDbStatus(DbStatusEnum.PENDING.getCode());
        dbResponseDto.setDbMessage(DbMessageEnum.PENDING.getMessage());
        dbResponseDto.setReason(DbMessageEnum.PENDING.getMessage());
        /*==============*/

        Optional<CustomerOrder> customerOrderOptional = customerOrderRepository.findById(customerOrderId);
        if (customerOrderOptional.isPresent())
        {
            CustomerOrder customerOrder = customerOrderOptional.get();
            /*Check if this order is canceled.
             *Only allow to create cancel reason for already canceled orders.*/
            if (customerOrder.getStatus().getId() == OrderStatusEnum.Canceled.getId())
            {
                CancelReason cancelReason = new CancelReason();
                cancelReason.setReason(reason);
                cancelReason.setOrder(customerOrder);
                try
                {
                    cancelReasonRepository.save(cancelReason);
                    dbResponseDto.setDbStatus(DbStatusEnum.SUCCESS.getCode());
                    dbResponseDto.setDbMessage(DbMessageEnum.SUCCESS.getMessage());
                    dbResponseDto.setReason(DbMessageEnum.SUCCESS.getMessage());
                } catch (Exception e)
                {
                    dbResponseDto.setDbStatus(DbStatusEnum.FAILED.getCode());
                    dbResponseDto.setDbMessage(DbMessageEnum.FAILED.getMessage());
                    dbResponseDto.setReason(e.getMessage());
                }
            } else
            {
                dbResponseDto.setReason(InputValidateMessageEnum.ORDER_NOT_CANCELED.getMessage());
            }
        } else
        {
            dbResponseDto.setReason(InputValidateMessageEnum.ORDER_NOT_FOUND.getMessage());
        }
        return dbResponseDto;
    }

    @Override
    public CancelReasonDto getCancelReason(Long customerOrderId)
    {
        Optional<CancelReasonDto> cancelReasonDtoOptional = cancelReasonRepository.findByOrderId(customerOrderId)
                .map(cancelReason -> objectMapper.convertValue(cancelReason, CancelReasonDto.class));
        return cancelReasonDtoOptional.orElseGet(CancelReasonDto::new);
    }
}
