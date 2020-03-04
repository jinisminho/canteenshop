package com.spring2020.customerapp.controller;

import com.spring2020.customerapp.domain.dto.ProductDto;
import com.spring2020.customerapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/find")
    public Page<ProductDto> findProducts(@RequestParam(value = "Product name", required = false) String name,
                                         Pageable pageable)
    {
        return productService.findProduct(pageable, name);
    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable("id") Long id)
    {
        return productService.findProductById(id);
    }

}
