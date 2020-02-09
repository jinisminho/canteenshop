package com.spring2020.staffwebapp.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring2020.staffwebapp.domain.dto.CategoryDto;
import com.spring2020.staffwebapp.domain.entity.Category;
import com.spring2020.staffwebapp.exception.MissingInputException;
import com.spring2020.staffwebapp.exception.ResourceNotFoundException;
import com.spring2020.staffwebapp.repository.CategoryRepository;
import com.spring2020.staffwebapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService
{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ObjectMapper mapper;

//    @Override
//    public CategoryDto createCategory(CategoryDto categoryDto) {
//        if (categoryDto == null) {
//            throw new MissingInputException("missing input");
//        }
//        Category category = mapper.convertValue(categoryDto, Category.class);
//        Category savedCategory = categoryRepository.save(category);
//        return mapper.convertValue(savedCategory, CategoryDto.class);
//    }
//
//    @Override
//    public void updateCategory(int id, CategoryDto categoryDto) {
//        if (categoryDto == null) {
//            throw new MissingInputException("missing input");
//        }
//        Category updatingCategory = findCategoryByIdReturnCategory(id);
//        updatingCategory.setName(categoryDto.getName());
//        categoryRepository.save(updatingCategory);
//    }
//
//
//    /**
//     * will throw exception if category still has child - product
//     *
//     * @param id category's id
//     */
//    @Override
//    public void deleteCategory(int id) {
//        Category category = findCategoryByIdReturnCategory(id);
//        categoryRepository.delete(category);
//    }
//
//    @Override
//    public CategoryDto findCategoryById(int id) {
//        return mapper.convertValue(findCategoryByIdReturnCategory(id), CategoryDto.class);
//    }

    @Override
    public Page<CategoryDto> findAllCategories(Pageable pageable)
    {
        return categoryRepository.findAll(pageable)
                .map(category -> mapper.convertValue(category, CategoryDto.class));
    }

    @Override
    public Page<CategoryDto> findCategoryByName(String name, Pageable pageable)
    {
        return categoryRepository.findByNameContaining(name, pageable)
                .map(category -> mapper.convertValue(category, CategoryDto.class));
    }

//    private Category findCategoryByIdReturnCategory(int id) {
//        return categoryRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Category with id: " + id + " not found"));
//    }
}
