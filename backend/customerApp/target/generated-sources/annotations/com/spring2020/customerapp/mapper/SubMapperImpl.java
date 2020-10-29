package com.spring2020.customerapp.mapper;

import com.spring2020.customerapp.domain.dto.InputNewOrderDetailDto;
import com.spring2020.customerapp.domain.dto.NewOrderDetailDto;
import com.spring2020.customerapp.domain.dto.ProductDto;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-27T14:34:54+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_211 (Oracle Corporation)"
)
public class SubMapperImpl implements SubMapper {

    @Override
    public NewOrderDetailDto dtoToDto(InputNewOrderDetailDto dto) {
        if ( dto == null ) {
            return null;
        }

        NewOrderDetailDto newOrderDetailDto = new NewOrderDetailDto();

        newOrderDetailDto.setProduct( inputNewOrderDetailDtoToProductDto( dto ) );
        newOrderDetailDto.setQuantity( dto.getQuantity() );

        return newOrderDetailDto;
    }

    protected ProductDto inputNewOrderDetailDtoToProductDto(InputNewOrderDetailDto inputNewOrderDetailDto) {
        if ( inputNewOrderDetailDto == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setId( inputNewOrderDetailDto.getProduct() );

        return productDto;
    }
}
