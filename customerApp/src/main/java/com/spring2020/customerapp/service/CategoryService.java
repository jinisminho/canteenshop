package com.spring2020.customerapp.service;

import com.spring2020.customerapp.domain.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    Page<CategoryDto> findAllCategories(Pageable pageable);

}
