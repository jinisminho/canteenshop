package com.spring2020.customerapp.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring2020.customerapp.domain.dto.*;
import com.spring2020.customerapp.domain.entity.*;
import com.spring2020.customerapp.domain.enums.OrderStatusEnum;
import com.spring2020.customerapp.exception.CommonException;
import com.spring2020.customerapp.exception.MissingInputException;
import com.spring2020.customerapp.mapper.OrderMapper;
import com.spring2020.customerapp.mapper.ProductMapper;
import com.spring2020.customerapp.repository.*;
import com.spring2020.customerapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private CancelReasonRepository cancelReasonRepository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public OrderDto createOrder(InputCreateOrderDto orderDto) {

        if (orderDto == null) {
            throw new MissingInputException("missing input");
        }
        Customer customer = customerRepository.findByAppUser_Id(orderDto.getCustomerId())
                .orElseThrow(() -> new CommonException("AppUserId is not Available"));
        ;
        orderDto.setCustomerId(customer.getId());
        CreateOrderDto createOrderDto = OrderMapper.INSTANCE.dtoToDto(orderDto);
        List<NewOrderDetailDto> listDetailFromDb = createOrderDto.getOrderDetails();
        double totalPrice = 0;
        for (NewOrderDetailDto newOrderDetailDto : listDetailFromDb) {
            ProductDto productDto = ProductMapper.INSTANCE.toDto(productRepository.findById(newOrderDetailDto.getProduct().getId()).get());
            listDetailFromDb.get(listDetailFromDb.indexOf(newOrderDetailDto)).setProduct(productDto);
            totalPrice += (productDto.getPrice() * listDetailFromDb.get(listDetailFromDb.indexOf(newOrderDetailDto)).getQuantity());
        }
        createOrderDto.setOrderDetails(listDetailFromDb);

//        if (orderDto.getTotalPrice() != totalPrice) {
//            throw new CommonException("Total Price is not correct");
//        }
//        orderDto.setTotalPrice(totalPrice);
        createOrderDto.setTotalPrice(totalPrice);

        CustomerOrder customerOrder = OrderMapper.INSTANCE.toEntity(createOrderDto);

        customerOrder.setStatus(new OrderStatus(1, OrderStatusEnum.Pending));

        OrderDto savedOrder = OrderMapper.INSTANCE.toOrderDto(customerOrderRepository.saveAndFlush(customerOrder));

        List<Long> listDetatilId = savedOrder.getOrderDetails().stream().map(detail -> detail.getId()).collect(Collectors.toList());
        List<OrderDetail> listCreatedDetail = new ArrayList<>();
        List<OrderDetailDto> listDetailDto = new ArrayList<>();

        for (Long id : listDetatilId) {
            listCreatedDetail.add(orderDetailRepository.getOne(id));
        }
        for (OrderDetail orderDetail : listCreatedDetail) {
            orderDetail.setCustomerOrder(customerOrderRepository.getOne(savedOrder.getId()));
            listDetailDto.add(OrderMapper.INSTANCE.toDetailDto(orderDetailRepository.saveAndFlush(orderDetail)));
        }

        savedOrder.setOrderDetails(listDetailDto);

        return savedOrder;

    }

    @Override
    public void cancelOrder(Long id) {
        CustomerOrder order = customerOrderRepository.getOne(id);
        if (order.getStatus().getStatus().equals(OrderStatusEnum.Pending)) {
            order.setStatus(new OrderStatus(5, OrderStatusEnum.Canceled));
            customerOrderRepository.saveAndFlush(order);
        }
    }

    @Override
    public OrderDetailDto viewOrderDetail(Long id) {
        return OrderMapper.INSTANCE.toDetailDto(orderDetailRepository.getOne(id));
    }

    @Override
    public List<OrderDto> viewOrderHistory(long appUserId) {
        Customer customer = customerRepository.findByAppUser_Id(appUserId)
                .orElseThrow(() -> new CommonException("AppUserId is not Available"));
        List<CustomerOrder> result = customerOrderRepository.findCustomerOrdersByCustomer(customer.getId());
        return result.stream().map(customerOrder -> mapper.convertValue(customerOrder, OrderDto.class)).collect(Collectors.toList());

    }

    @Override
    public String cancelOrder(CancelOrderDto dto) {
        CustomerOrder order = customerOrderRepository.findById(dto.getOrderId()).orElseThrow(
                () -> new CommonException("Order not found")
        );
        if(!order.getStatus().getStatus().equals(OrderStatusEnum.Pending)){
            throw new  CommonException("Customer's only allowed to cancel pending orders");
        }
        order.setStatus(new OrderStatus(5, OrderStatusEnum.Canceled));
        customerOrderRepository.saveAndFlush(order);
        CancelReason cancelReason = new CancelReason();
        cancelReason.setOrder(order);
        cancelReason.setReason("Customer: " + dto.getReason());
        cancelReason.setCancelAt(LocalDateTime.now());
        cancelReasonRepository.saveAndFlush(cancelReason);
        return "Canceled successful";
    }


}
