package com.spring2020.coffeeshop.controller;

import com.spring2020.coffeeshop.domain.dto.CategoryDto;
import com.spring2020.coffeeshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.spring2020.coffeeshop.util.ConstantUtil.DELETE_SUCCESS;
import static com.spring2020.coffeeshop.util.ConstantUtil.UPDATE_SUCCESS;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public CategoryDto createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        return categoryService.createCategory(categoryDto);
    }

    @PutMapping("/{id}")
    public String updateCategory(@PathVariable(value = "id") int id,
                                 @Valid @RequestBody CategoryDto categoryDto) {
        categoryService.updateCategory(id, categoryDto);
        return UPDATE_SUCCESS;
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable(value = "id") int id) {
        categoryService.deleteCategory(id);
        return DELETE_SUCCESS;
    }

    @GetMapping("/{id}")
    public CategoryDto findCategoryById(@PathVariable(value = "id") int id) {
        return categoryService.findCategoryById(id);
    }

    @GetMapping
    public Page<CategoryDto> findCategory(@RequestParam(required = false, value = "name") String name,
                                          Pageable pageable) {
        if (name != null) {
            return categoryService.findCategoryByName(name, pageable);
        }
        return categoryService.findAllCategories(pageable);
    }

}
