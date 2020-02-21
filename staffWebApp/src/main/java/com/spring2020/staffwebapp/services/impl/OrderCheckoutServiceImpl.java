package com.spring2020.staffwebapp.services.impl;

import com.spring2020.staffwebapp.domain.dto.DbResponseDto;
import com.spring2020.staffwebapp.domain.dto.OrderCheckoutDto;
import com.spring2020.staffwebapp.domain.dto.ProductDto;
import com.spring2020.staffwebapp.domain.entity.*;
import com.spring2020.staffwebapp.domain.enums.DbMessageEnum;
import com.spring2020.staffwebapp.domain.enums.DbStatusEnum;
import com.spring2020.staffwebapp.repositories.*;
import com.spring2020.staffwebapp.services.OrderCheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderCheckoutServiceImpl implements OrderCheckoutService
{
    @Autowired
    CustomerOrderRepository customerOrderRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    OrderStatusRepository orderStatusRepository;
    @Autowired
    StaffRepository staffRepository;
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    CustomerRepository customerRepository;

    private static long STAFF_CUSTOMER_ID = 1;

    @Override
    public DbResponseDto checkoutOrderStaff(OrderCheckoutDto request)
    {
        /*Set return database not used*/
        DbResponseDto dbResponseDto = new DbResponseDto();
        dbResponseDto.setDbStatus(DbStatusEnum.PENDING.getCode());
        dbResponseDto.setDbMessage(DbMessageEnum.PENDING.getMessage());
        dbResponseDto.setReason(DbMessageEnum.PENDING.getMessage());
        /*==============*/

        /*Get Customer order*/
        CustomerOrder customerOrder = createCustomerOrder(request);
        /*==============*/

        /*Get Order details list*/
        List<OrderDetail> orderDetailList = createOrderDetail(request, customerOrder);
        /*==============*/

        if (customerOrder != null && !orderDetailList.isEmpty())
        {
            try
            {
                customerOrderRepository.save(customerOrder);
                orderDetailRepository.saveAll(orderDetailList);
                dbResponseDto.setDbStatus(DbStatusEnum.SUCCESS.getCode());
                dbResponseDto.setDbMessage(DbMessageEnum.SUCCESS.getMessage());
                dbResponseDto.setReason(DbMessageEnum.SUCCESS.getMessage());
            } catch (Exception e)
            {
                dbResponseDto.setDbStatus(DbStatusEnum.FAILED.getCode());
                dbResponseDto.setDbMessage(DbMessageEnum.FAILED.getMessage());
                dbResponseDto.setReason(e.getMessage());
            }
        }

        return dbResponseDto;
    }

    private CustomerOrder createCustomerOrder(OrderCheckoutDto request)
    {
        CustomerOrder customerOrder = new CustomerOrder();

        /*Get Staff*/
        Optional<Staff> staff = getStaff(request.getCart().getStaffUsername());
        /*=============*/

//        Staff's customer Id
        Long customerId = STAFF_CUSTOMER_ID;
//        Get Customer
        Optional<Customer> customer = customerRepository.findById(customerId);

//        Total price for customer order table
        double totalPrice = 0;
        for (ProductDto productDto :
                request.getCart().getProductList())
        {
            totalPrice += productDto.getPrice() * productDto.getQuantity();
        }

        if (staff.isPresent() && customer.isPresent())
        {
            /*Set data for customer order*/
            customerOrder.setLocation(request.getLocation());
            customerOrder.setNote(request.getNote());
            customerOrder.setTotalPrice(totalPrice);
            customerOrder.setStaff(staff.get());
            customerOrder.setCustomer(customer.get());
            /*==========================*/
            return customerOrder;

        }else if (staff.isPresent()){
            /*Set data for customer order*/
            customerOrder.setLocation(request.getLocation());
            customerOrder.setNote(request.getNote());
            customerOrder.setTotalPrice(totalPrice);
            customerOrder.setStaff(staff.get());
            /*==========================*/
            return customerOrder;
        }
        return null;
    }

    private List<OrderDetail> createOrderDetail(OrderCheckoutDto request, CustomerOrder customerOrder)
    {
        /*Create Order details.
         * One customer order will have one (or many) order details.
         * One order detail will only belong to one customer order.*/
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail;
        for (ProductDto productDto :
                request.getCart().getProductList())
        {
            orderDetail = new OrderDetail();
            orderDetail.setQuantity(productDto.getQuantity());
            orderDetail.setCustomerOrder(customerOrder);
            Product product = new Product();
            product.setId(productDto.getId());
            orderDetail.setProduct(product);
            orderDetailList.add(orderDetail);
        }
        /*==============*/
        return orderDetailList;
    }

    private Optional<Staff> getStaff(String username)
    {
        Optional<AppUser> appUser = appUserRepository.findAppUserByUsername(username);
        if (appUser.isPresent())
        {
            Long appUserId = appUser.get().getId();
            Optional<Staff> staff = staffRepository.findStaffByAppUserId(appUserId);
            if (staff.isPresent())
            {
                return staff;
            }
        }
        return Optional.empty();
    }
}
