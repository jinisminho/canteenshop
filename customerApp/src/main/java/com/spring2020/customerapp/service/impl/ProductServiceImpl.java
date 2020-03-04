package com.spring2020.customerapp.service.impl;

import com.spring2020.customerapp.domain.dto.ProductDto;
import com.spring2020.customerapp.exception.ResourceNotFoundException;
import com.spring2020.customerapp.repository.ProductRepository;
import com.spring2020.customerapp.service.ProductService;
import com.spring2020.customerapp.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<ProductDto> findProduct(Pageable pageable, String name) {

        if(name != null) {
            if (!name.isEmpty()) {
                return productRepository.findByNameContainingAndAvailable(name, true, pageable)
                        .map(product -> ProductMapper.INSTANCE.toDto(product));
            }
        }

        return productRepository.findByAvailable(true, pageable)
                .map(product -> ProductMapper.INSTANCE.toDto(product));

    }

    @Override
    public ProductDto findProductById(Long id) {
        ProductDto result = new ProductDto();
        if(id != null) {
            result = ProductMapper.INSTANCE.toDto(productRepository.findByIdAndAvailable(id, true));
        }

        if (result != null) {
            if(result.isAvailable()) {
                return result;
            }
        }

        throw new ResourceNotFoundException("Cant find product = id");
    }
}
