package com.spring2020.customerapp.service;

import com.spring2020.customerapp.domain.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<ProductDto> findProduct(Pageable pageable, String name);

}
