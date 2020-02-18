package com.spring2020.customerapp.mapper;

import com.spring2020.customerapp.domain.dto.ProductDto;
import com.spring2020.customerapp.domain.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper( ProductMapper.class );

    ProductDto toDto(Product product);

    Product toEntity(ProductDto product);
}
