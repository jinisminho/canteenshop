package com.spring2020.staffwebapp.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring2020.staffwebapp.domain.dto.DbResponseDto;
import com.spring2020.staffwebapp.domain.dto.OrderDetailsDto;
import com.spring2020.staffwebapp.domain.entity.AppUser;
import com.spring2020.staffwebapp.domain.entity.CustomerOrder;
import com.spring2020.staffwebapp.domain.entity.OrderStatus;
import com.spring2020.staffwebapp.domain.entity.Staff;
import com.spring2020.staffwebapp.domain.enums.DbMessageEnum;
import com.spring2020.staffwebapp.domain.enums.DbStatusEnum;
import com.spring2020.staffwebapp.domain.enums.InputValidateMessageEnum;
import com.spring2020.staffwebapp.domain.enums.OrderStatusEnum;
import com.spring2020.staffwebapp.exception.MissingInputException;
import com.spring2020.staffwebapp.repository.AppUserRepository;
import com.spring2020.staffwebapp.repository.CustomerOrderRepository;
import com.spring2020.staffwebapp.repository.OrderStatusRepository;
import com.spring2020.staffwebapp.repository.StaffRepository;
import com.spring2020.staffwebapp.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Service
public class OrderInfoServiceImpl implements OrderInfoService
{
    @Autowired
    CustomerOrderRepository customerOrderRepository;
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    StaffRepository staffRepository;
    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public OrderDetailsDto viewOrderDetails(Long id)
    {
        OrderDetailsDto orderDetailsDto = new OrderDetailsDto();
        Optional<CustomerOrder> customerOrderOptional = customerOrderRepository.findById(id);
        if (customerOrderOptional.isPresent())
        {
            Optional<OrderDetailsDto> orderDetailsDtoOptional = customerOrderOptional
                    .map(orderDetail -> objectMapper.convertValue(orderDetail, OrderDetailsDto.class));
            if (orderDetailsDtoOptional.isPresent())
            {
                orderDetailsDto = orderDetailsDtoOptional.get();
            }
        }
        return orderDetailsDto;
    }

    @Override
    public Page<OrderDetailsDto> findOrdersByStaff(String username, Pageable pageable)
    {
        Optional<AppUser> appUserOptional = appUserRepository.findAppUserByUsername(username);
        if (appUserOptional.isPresent())
        {
            Optional<Staff> staffOptional = staffRepository.findStaffByAppUserId(appUserOptional.get().getId());
            if (staffOptional.isPresent())
            {
                return customerOrderRepository.findAllByStaffId(staffOptional.get().getId(), pageable)
                        .map(customerOrder -> objectMapper.convertValue(customerOrder, OrderDetailsDto.class));
            }
        }
        return Page.empty(pageable);
    }

    @Override
    public Page<OrderDetailsDto> findOrdersInPeriod(String from, String to, Pageable pageable)
    {
        try
        {
            LocalDate fromDate = LocalDate.parse(from);
            LocalDate toDate = LocalDate.parse(to);
            return customerOrderRepository.findAllByCreateAtIsBetween(fromDate.atStartOfDay()
                    , toDate.atTime(23, 59, 59), pageable)
                    .map(customerOrder -> objectMapper.convertValue(customerOrder, OrderDetailsDto.class));
        } catch (DateTimeParseException e)
        {
            throw new MissingInputException(e.getMessage());
        }
    }

    @Override
    public Page<OrderDetailsDto> findOrdersByStatus(OrderStatusEnum orderStatusEnum, Pageable pageable)
    {
        return customerOrderRepository.findAllByStatusId(orderStatusEnum.getId(), pageable)
                .map(customerOrder -> objectMapper.convertValue(customerOrder, OrderDetailsDto.class));
    }

    @Override
    public DbResponseDto editOrderStatus(Long id, OrderStatusEnum orderStatusEnum)
    {
        /*Set return database not used*/
        DbResponseDto dbResponseDto = new DbResponseDto();
        dbResponseDto.setDbStatus(DbStatusEnum.PENDING.getCode());
        dbResponseDto.setDbMessage(DbMessageEnum.PENDING.getMessage());
        dbResponseDto.setReason(DbMessageEnum.PENDING.getMessage());
        /*==============*/

        Optional<CustomerOrder> customerOrderOptional = customerOrderRepository.findById(id);
        if (customerOrderOptional.isPresent())
        {
            Optional<OrderStatus> orderStatusOptional = orderStatusRepository.findById(orderStatusEnum.getId());
            if (orderStatusOptional.isPresent())
            {
                CustomerOrder customerOrder = customerOrderOptional.get();
                /*Check if order is canceled or completed.
                 * NOT allowed to change canceled or completed orders*/
                if (customerOrder.getStatus().getId().equals(OrderStatusEnum.Completed.getId())
                        || customerOrder.getStatus().getId().equals(OrderStatusEnum.Canceled.getId()))
                {
                    customerOrder.setStatus(orderStatusOptional.get());
                    try
                    {
                        customerOrderRepository.save(customerOrder);
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
                    dbResponseDto.setReason(InputValidateMessageEnum.CHANGE_ORDER_STT_COMPLETED_CANCELED.getMessage());
                }
            } else
            {
                dbResponseDto.setReason(InputValidateMessageEnum.ORDER_STATUS_NOT_FOUND.getMessage());
            }
        } else
        {
            dbResponseDto.setReason(InputValidateMessageEnum.ORDER_NOT_FOUND.getMessage());
        }
        return dbResponseDto;
    }
}
