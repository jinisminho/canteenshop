package com.spring2020.customerapp.mapper;

import com.spring2020.customerapp.domain.dto.CategoryDto;
import com.spring2020.customerapp.domain.entity.Category;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-27T14:34:54+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_211 (Oracle Corporation)"
)
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDto toDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId( category.getId() );
        categoryDto.setName( category.getName() );

        return categoryDto;
    }

    @Override
    public Category toEntity(CategoryDto category) {
        if ( category == null ) {
            return null;
        }

        Category category1 = new Category();

        category1.setId( category.getId() );
        category1.setName( category.getName() );

        return category1;
    }
}
