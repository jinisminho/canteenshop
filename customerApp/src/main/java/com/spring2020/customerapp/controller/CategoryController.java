package com.spring2020.customerapp.controller;

import com.spring2020.customerapp.domain.dto.CategoryDto;
import com.spring2020.customerapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Page<CategoryDto> findCategory(Pageable pageable) {
        return categoryService.findAllCategories(pageable);
    }
}
