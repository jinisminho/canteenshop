package com.spring2020.customerapp.service.impl;

import com.spring2020.customerapp.domain.dto.CategoryDto;
import com.spring2020.customerapp.repository.CategoryRepository;
import com.spring2020.customerapp.service.CategoryService;
import com.spring2020.customerapp.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<CategoryDto> findAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable)
                .map(category -> CategoryMapper.INSTANCE.toDto(category));
    }

}
