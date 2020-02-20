package com.spring2020.staffwebapp.controllers;

import com.spring2020.staffwebapp.domain.dto.CategoryDto;
import com.spring2020.staffwebapp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

//    @PostMapping
//    public CategoryDto createCategory(@Valid @RequestBody CategoryDto categoryDto) {
//        return categoryService.createCategory(categoryDto);
//    }
//
//    @PutMapping("/{id}")
//    public String updateCategory(@PathVariable(value = "id") int id,
//                                 @Valid @RequestBody CategoryDto categoryDto) {
//        categoryService.updateCategory(id, categoryDto);
//        return UPDATE_SUCCESS;
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteCategory(@PathVariable(value = "id") int id) {
//        categoryService.deleteCategory(id);
//        return DELETE_SUCCESS;
//    }
//
//    @GetMapping("/{id}")
//    public CategoryDto findCategoryById(@PathVariable(value = "id") int id) {
//        return categoryService.findCategoryById(id);
//    }

    @GetMapping("/search")
    public Page<CategoryDto> findCategory(@RequestParam(required = false, value = "name") String name,
                                          Pageable pageable) {
        if (name != null) {
            return categoryService.findCategoryByName(name, pageable);
        }
        return categoryService.findAllCategories(pageable);
    }

}
