package com.spring2020.customerapp.mapper;

import com.spring2020.customerapp.domain.dto.CategoryDto;
import com.spring2020.customerapp.domain.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper( CategoryMapper.class );

    CategoryDto toDto(Category category);

    Category toEntity(CategoryDto category);

}
