package com.spring2020.customerapp.service.impl;

import com.spring2020.customerapp.domain.dto.*;
import com.spring2020.customerapp.domain.entity.CustomerOrder;
import com.spring2020.customerapp.domain.entity.OrderDetail;
import com.spring2020.customerapp.domain.entity.OrderStatus;
import com.spring2020.customerapp.domain.enums.OrderStatusEnum;
import com.spring2020.customerapp.exception.CommonException;
import com.spring2020.customerapp.exception.MissingInputException;
import com.spring2020.customerapp.mapper.ProductMapper;
import com.spring2020.customerapp.repository.*;
import com.spring2020.customerapp.service.OrderService;
import com.spring2020.customerapp.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    @Override
    public OrderDto createOrder(InputCreateOrderDto orderDto) {

        if (orderDto == null) {
            throw new MissingInputException("missing input");
        }
        if(!customerRepository.findById(orderDto.getCustomerId()).isPresent()) {
            throw new CommonException("CustomerId is not Available");
        }

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
        if(order.getStatus().getStatus().equals(OrderStatusEnum.Pending)) {
            order.setStatus(new OrderStatus(5, OrderStatusEnum.Canceled));
            customerOrderRepository.saveAndFlush(order);
        }
    }

    @Override
    public OrderDetailDto viewOrderDetail(Long id) {
        return OrderMapper.INSTANCE.toDetailDto(orderDetailRepository.getOne(id));
    }

    @Override
    public Page<OrderDto> viewOrderHistory(Pageable pageable, int customerId) {
        return customerOrderRepository.findCustomerOrdersByCustomer(customerId, pageable)
                .map(order -> OrderMapper.INSTANCE.toOrderDto(order));
    }


}
