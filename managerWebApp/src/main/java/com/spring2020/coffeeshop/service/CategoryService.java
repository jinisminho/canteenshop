package com.spring2020.coffeeshop.service;

import com.spring2020.coffeeshop.domain.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    void updateCategory(int id, CategoryDto categoryDto);

    void deleteCategory(int id);

    CategoryDto findCategoryById(int id);

    Page<CategoryDto> findAllCategories(Pageable pageable);

    Page<CategoryDto> findCategoryByName(String name, Pageable pageable);


    List<CategoryDto> findAll();

}
