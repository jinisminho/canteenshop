package com.spring2020.staffwebapp.service;

import com.spring2020.staffwebapp.domain.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

//    CategoryDto createCategory(CategoryDto categoryDto);
//
//    void updateCategory(int id, CategoryDto categoryDto);
//
//    void deleteCategory(int id);
//
//    CategoryDto findCategoryById(int id);

    Page<CategoryDto> findAllCategories(Pageable pageable);

    Page<CategoryDto> findCategoryByName(String name, Pageable pageable);

}
