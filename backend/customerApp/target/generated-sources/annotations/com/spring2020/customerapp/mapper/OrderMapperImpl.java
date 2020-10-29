package com.spring2020.customerapp.mapper;

import com.spring2020.customerapp.domain.dto.AppUserDto;
import com.spring2020.customerapp.domain.dto.CategoryDto;
import com.spring2020.customerapp.domain.dto.CreateOrderDto;
import com.spring2020.customerapp.domain.dto.CustomerDto;
import com.spring2020.customerapp.domain.dto.InputCreateOrderDto;
import com.spring2020.customerapp.domain.dto.InputNewOrderDetailDto;
import com.spring2020.customerapp.domain.dto.NewOrderDetailDto;
import com.spring2020.customerapp.domain.dto.OrderDetailDto;
import com.spring2020.customerapp.domain.dto.OrderDto;
import com.spring2020.customerapp.domain.dto.OrderStatusDto;
import com.spring2020.customerapp.domain.dto.ProductDto;
import com.spring2020.customerapp.domain.dto.StaffAppUserDto;
import com.spring2020.customerapp.domain.dto.StaffDto;
import com.spring2020.customerapp.domain.entity.AppUser;
import com.spring2020.customerapp.domain.entity.Category;
import com.spring2020.customerapp.domain.entity.Customer;
import com.spring2020.customerapp.domain.entity.CustomerOrder;
import com.spring2020.customerapp.domain.entity.OrderDetail;
import com.spring2020.customerapp.domain.entity.OrderStatus;
import com.spring2020.customerapp.domain.entity.Product;
import com.spring2020.customerapp.domain.entity.Staff;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.mapstruct.factory.Mappers;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-27T14:34:55+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_211 (Oracle Corporation)"
)
public class OrderMapperImpl implements OrderMapper {

    private final SubMapper subMapper = Mappers.getMapper( SubMapper.class );

    @Override
    public OrderDetailDto toDetailDto(OrderDetail order) {
        if ( order == null ) {
            return null;
        }

        OrderDetailDto orderDetailDto = new OrderDetailDto();

        orderDetailDto.setId( order.getId() );
        orderDetailDto.setQuantity( order.getQuantity() );
        orderDetailDto.setProduct( productToProductDto( order.getProduct() ) );

        return orderDetailDto;
    }

    @Override
    public OrderDetail toEntity(OrderDetailDto order) {
        if ( order == null ) {
            return null;
        }

        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setId( order.getId() );
        orderDetail.setQuantity( order.getQuantity() );
        orderDetail.setProduct( productDtoToProduct( order.getProduct() ) );

        return orderDetail;
    }

    @Override
    public OrderDto toOrderDto(CustomerOrder order) {
        if ( order == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setCreateAt( order.getCreateAt() );
        orderDto.setUpdateAt( order.getUpdateAt() );
        orderDto.setId( order.getId() );
        orderDto.setLocation( order.getLocation() );
        orderDto.setNote( order.getNote() );
        orderDto.setTotalPrice( order.getTotalPrice() );
        orderDto.setOrderDetails( orderDetailListToOrderDetailDtoList( order.getOrderDetails() ) );
        orderDto.setStatus( orderStatusToOrderStatusDto( order.getStatus() ) );
        orderDto.setStaff( staffToStaffDto( order.getStaff() ) );
        orderDto.setCustomer( customerToCustomerDto( order.getCustomer() ) );

        return orderDto;
    }

    @Override
    public CustomerOrder toEntity(OrderDto order) {
        if ( order == null ) {
            return null;
        }

        CustomerOrder customerOrder = new CustomerOrder();

        customerOrder.setCreateAt( order.getCreateAt() );
        customerOrder.setUpdateAt( order.getUpdateAt() );
        customerOrder.setId( order.getId() );
        customerOrder.setLocation( order.getLocation() );
        customerOrder.setNote( order.getNote() );
        customerOrder.setTotalPrice( order.getTotalPrice() );
        customerOrder.setOrderDetails( orderDetailDtoListToOrderDetailList( order.getOrderDetails() ) );
        customerOrder.setStatus( orderStatusDtoToOrderStatus( order.getStatus() ) );
        customerOrder.setStaff( staffDtoToStaff( order.getStaff() ) );
        customerOrder.setCustomer( customerDtoToCustomer( order.getCustomer() ) );

        return customerOrder;
    }

    @Override
    public CreateOrderDto toCreateOrderDto(CustomerOrder order) {
        if ( order == null ) {
            return null;
        }

        CreateOrderDto createOrderDto = new CreateOrderDto();

        createOrderDto.setCustomerId( orderCustomerId( order ) );
        createOrderDto.setLocation( order.getLocation() );
        createOrderDto.setNote( order.getNote() );
        createOrderDto.setTotalPrice( order.getTotalPrice() );
        createOrderDto.setOrderDetails( orderDetailListToNewOrderDetailDtoList( order.getOrderDetails() ) );

        return createOrderDto;
    }

    @Override
    public CustomerOrder toEntity(CreateOrderDto order) {
        if ( order == null ) {
            return null;
        }

        CustomerOrder customerOrder = new CustomerOrder();

        customerOrder.setCustomer( createOrderDtoToCustomer( order ) );
        customerOrder.setLocation( order.getLocation() );
        customerOrder.setNote( order.getNote() );
        customerOrder.setTotalPrice( order.getTotalPrice() );
        customerOrder.setOrderDetails( newOrderDetailDtoListToOrderDetailList( order.getOrderDetails() ) );

        return customerOrder;
    }

    @Override
    public CreateOrderDto dtoToDto(InputCreateOrderDto dto) {
        if ( dto == null ) {
            return null;
        }

        CreateOrderDto createOrderDto = new CreateOrderDto();

        createOrderDto.setLocation( dto.getLocation() );
        createOrderDto.setNote( dto.getNote() );
        createOrderDto.setOrderDetails( inputNewOrderDetailDtoListToNewOrderDetailDtoList( dto.getOrderDetails() ) );
        createOrderDto.setCustomerId( dto.getCustomerId() );

        return createOrderDto;
    }

    protected CategoryDto categoryToCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId( category.getId() );
        categoryDto.setName( category.getName() );

        return categoryDto;
    }

    protected ProductDto productToProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setId( product.getId() );
        productDto.setName( product.getName() );
        productDto.setPrice( product.getPrice() );
        productDto.setDescription( product.getDescription() );
        productDto.setAvailable( product.isAvailable() );
        productDto.setCategory( categoryToCategoryDto( product.getCategory() ) );
        productDto.setUrl_img( product.getUrl_img() );

        return productDto;
    }

    protected Category categoryDtoToCategory(CategoryDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryDto.getId() );
        category.setName( categoryDto.getName() );

        return category;
    }

    protected Product productDtoToProduct(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productDto.getId() );
        product.setName( productDto.getName() );
        product.setPrice( productDto.getPrice() );
        product.setDescription( productDto.getDescription() );
        product.setAvailable( productDto.isAvailable() );
        product.setCategory( categoryDtoToCategory( productDto.getCategory() ) );
        product.setUrl_img( productDto.getUrl_img() );

        return product;
    }

    protected List<OrderDetailDto> orderDetailListToOrderDetailDtoList(List<OrderDetail> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderDetailDto> list1 = new ArrayList<OrderDetailDto>( list.size() );
        for ( OrderDetail orderDetail : list ) {
            list1.add( toDetailDto( orderDetail ) );
        }

        return list1;
    }

    protected OrderStatusDto orderStatusToOrderStatusDto(OrderStatus orderStatus) {
        if ( orderStatus == null ) {
            return null;
        }

        OrderStatusDto orderStatusDto = new OrderStatusDto();

        orderStatusDto.setId( orderStatus.getId() );
        orderStatusDto.setStatus( orderStatus.getStatus() );

        return orderStatusDto;
    }

    protected StaffAppUserDto appUserToStaffAppUserDto(AppUser appUser) {
        if ( appUser == null ) {
            return null;
        }

        StaffAppUserDto staffAppUserDto = new StaffAppUserDto();

        staffAppUserDto.setId( appUser.getId() );
        staffAppUserDto.setUsername( appUser.getUsername() );
        staffAppUserDto.setPassword( appUser.getPassword() );
        staffAppUserDto.setFirstName( appUser.getFirstName() );
        staffAppUserDto.setLastName( appUser.getLastName() );
        staffAppUserDto.setPhone( appUser.getPhone() );
        staffAppUserDto.setEmail( appUser.getEmail() );
        staffAppUserDto.setGender( appUser.getGender() );
        staffAppUserDto.setActive( appUser.isActive() );

        return staffAppUserDto;
    }

    protected StaffDto staffToStaffDto(Staff staff) {
        if ( staff == null ) {
            return null;
        }

        StaffDto staffDto = new StaffDto();

        staffDto.setId( staff.getId() );
        staffDto.setDob( staff.getDob() );
        staffDto.setAddress( staff.getAddress() );
        staffDto.setSocialId( staff.getSocialId() );
        staffDto.setHireDate( staff.getHireDate() );
        staffDto.setTerminateDate( staff.getTerminateDate() );
        staffDto.setAppUser( appUserToStaffAppUserDto( staff.getAppUser() ) );

        return staffDto;
    }

    protected AppUserDto appUserToAppUserDto(AppUser appUser) {
        if ( appUser == null ) {
            return null;
        }

        AppUserDto appUserDto = new AppUserDto();

        appUserDto.setId( appUser.getId() );
        appUserDto.setUsername( appUser.getUsername() );
        appUserDto.setFirstName( appUser.getFirstName() );
        appUserDto.setLastName( appUser.getLastName() );
        appUserDto.setPhone( appUser.getPhone() );
        appUserDto.setEmail( appUser.getEmail() );
        appUserDto.setGender( appUser.getGender() );
        appUserDto.setActive( appUser.isActive() );

        return appUserDto;
    }

    protected CustomerDto customerToCustomerDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();

        customerDto.setId( customer.getId() );
        customerDto.setAppUser( appUserToAppUserDto( customer.getAppUser() ) );

        return customerDto;
    }

    protected List<OrderDetail> orderDetailDtoListToOrderDetailList(List<OrderDetailDto> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderDetail> list1 = new ArrayList<OrderDetail>( list.size() );
        for ( OrderDetailDto orderDetailDto : list ) {
            list1.add( toEntity( orderDetailDto ) );
        }

        return list1;
    }

    protected OrderStatus orderStatusDtoToOrderStatus(OrderStatusDto orderStatusDto) {
        if ( orderStatusDto == null ) {
            return null;
        }

        OrderStatus orderStatus = new OrderStatus();

        orderStatus.setId( orderStatusDto.getId() );
        orderStatus.setStatus( orderStatusDto.getStatus() );

        return orderStatus;
    }

    protected AppUser staffAppUserDtoToAppUser(StaffAppUserDto staffAppUserDto) {
        if ( staffAppUserDto == null ) {
            return null;
        }

        AppUser appUser = new AppUser();

        appUser.setId( staffAppUserDto.getId() );
        appUser.setUsername( staffAppUserDto.getUsername() );
        appUser.setPassword( staffAppUserDto.getPassword() );
        appUser.setFirstName( staffAppUserDto.getFirstName() );
        appUser.setLastName( staffAppUserDto.getLastName() );
        appUser.setPhone( staffAppUserDto.getPhone() );
        appUser.setEmail( staffAppUserDto.getEmail() );
        appUser.setGender( staffAppUserDto.getGender() );
        appUser.setActive( staffAppUserDto.isActive() );

        return appUser;
    }

    protected Staff staffDtoToStaff(StaffDto staffDto) {
        if ( staffDto == null ) {
            return null;
        }

        Staff staff = new Staff();

        staff.setId( staffDto.getId() );
        staff.setDob( staffDto.getDob() );
        staff.setAddress( staffDto.getAddress() );
        staff.setSocialId( staffDto.getSocialId() );
        staff.setHireDate( staffDto.getHireDate() );
        staff.setTerminateDate( staffDto.getTerminateDate() );
        staff.setAppUser( staffAppUserDtoToAppUser( staffDto.getAppUser() ) );

        return staff;
    }

    protected AppUser appUserDtoToAppUser(AppUserDto appUserDto) {
        if ( appUserDto == null ) {
            return null;
        }

        AppUser appUser = new AppUser();

        appUser.setId( appUserDto.getId() );
        appUser.setUsername( appUserDto.getUsername() );
        appUser.setFirstName( appUserDto.getFirstName() );
        appUser.setLastName( appUserDto.getLastName() );
        appUser.setPhone( appUserDto.getPhone() );
        appUser.setEmail( appUserDto.getEmail() );
        appUser.setGender( appUserDto.getGender() );
        appUser.setActive( appUserDto.isActive() );

        return appUser;
    }

    protected Customer customerDtoToCustomer(CustomerDto customerDto) {
        if ( customerDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( customerDto.getId() );
        customer.setAppUser( appUserDtoToAppUser( customerDto.getAppUser() ) );

        return customer;
    }

    private int orderCustomerId(CustomerOrder customerOrder) {
        if ( customerOrder == null ) {
            return 0;
        }
        Customer customer = customerOrder.getCustomer();
        if ( customer == null ) {
            return 0;
        }
        int id = customer.getId();
        return id;
    }

    protected NewOrderDetailDto orderDetailToNewOrderDetailDto(OrderDetail orderDetail) {
        if ( orderDetail == null ) {
            return null;
        }

        NewOrderDetailDto newOrderDetailDto = new NewOrderDetailDto();

        newOrderDetailDto.setQuantity( orderDetail.getQuantity() );
        newOrderDetailDto.setProduct( productToProductDto( orderDetail.getProduct() ) );

        return newOrderDetailDto;
    }

    protected List<NewOrderDetailDto> orderDetailListToNewOrderDetailDtoList(List<OrderDetail> list) {
        if ( list == null ) {
            return null;
        }

        List<NewOrderDetailDto> list1 = new ArrayList<NewOrderDetailDto>( list.size() );
        for ( OrderDetail orderDetail : list ) {
            list1.add( orderDetailToNewOrderDetailDto( orderDetail ) );
        }

        return list1;
    }

    protected Customer createOrderDtoToCustomer(CreateOrderDto createOrderDto) {
        if ( createOrderDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( (int) createOrderDto.getCustomerId() );

        return customer;
    }

    protected OrderDetail newOrderDetailDtoToOrderDetail(NewOrderDetailDto newOrderDetailDto) {
        if ( newOrderDetailDto == null ) {
            return null;
        }

        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setQuantity( newOrderDetailDto.getQuantity() );
        orderDetail.setProduct( productDtoToProduct( newOrderDetailDto.getProduct() ) );

        return orderDetail;
    }

    protected List<OrderDetail> newOrderDetailDtoListToOrderDetailList(List<NewOrderDetailDto> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderDetail> list1 = new ArrayList<OrderDetail>( list.size() );
        for ( NewOrderDetailDto newOrderDetailDto : list ) {
            list1.add( newOrderDetailDtoToOrderDetail( newOrderDetailDto ) );
        }

        return list1;
    }

    protected List<NewOrderDetailDto> inputNewOrderDetailDtoListToNewOrderDetailDtoList(List<InputNewOrderDetailDto> list) {
        if ( list == null ) {
            return null;
        }

        List<NewOrderDetailDto> list1 = new ArrayList<NewOrderDetailDto>( list.size() );
        for ( InputNewOrderDetailDto inputNewOrderDetailDto : list ) {
            list1.add( subMapper.dtoToDto( inputNewOrderDetailDto ) );
        }

        return list1;
    }
}
