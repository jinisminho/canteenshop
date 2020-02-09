package com.spring2020.staffwebapp.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring2020.staffwebapp.domain.dto.ProductDto;
import com.spring2020.staffwebapp.repositories.ProductRepository;
import com.spring2020.staffwebapp.services.ProductRetrieveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductRetrieveServiceImpl implements ProductRetrieveService
{
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public Page<ProductDto> findAllProducts(Pageable pageable)
    {
        return productRepository.findAll(pageable)
                .map(product -> objectMapper.convertValue(product, ProductDto.class));
    }


    @Override
    public Page<ProductDto> findProducts(Pageable pageable, String name, Integer categoryId, Boolean isAvailable)
    {
        if (name != null)
        {
            name = "%" + name + "%";
        } else
        {
            name = "";
        }

        String categoryIdString = "";
        if (categoryId != null)
        {
            categoryIdString = String.valueOf(categoryId);
        }

        return productRepository.findProducts(name, categoryIdString, isAvailable, pageable)
                .map(product -> objectMapper.convertValue(product, ProductDto.class));
    }

    @Override
    public ProductDto showProductDetails(Long id)
    {
        Optional<ProductDto> productDtoOptional = productRepository.findById(id)
                .map(product -> objectMapper.convertValue(product, ProductDto.class));
        return productDtoOptional.orElseGet(ProductDto::new);
    }
}
