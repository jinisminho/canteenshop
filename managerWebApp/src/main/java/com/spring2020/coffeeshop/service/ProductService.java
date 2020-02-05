package com.spring2020.coffeeshop.service;

import com.spring2020.coffeeshop.domain.dto.ProductDto;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto);

    void updateProductData(long id, ProductDto productDto);

    void updateProductImage(long id, MultipartFile file);

    ProductDto findProductDataById(long id);

    Resource findProductImageById(long id);

    Page<ProductDto> findAllProduct(Pageable pageable);

    Page<ProductDto> findProductByNameOrCategory(String name, long categoryId, Pageable pageable);

}
