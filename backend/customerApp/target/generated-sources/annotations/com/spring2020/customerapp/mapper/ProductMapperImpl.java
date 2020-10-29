package com.spring2020.customerapp.mapper;

import com.spring2020.customerapp.domain.dto.CategoryDto;
import com.spring2020.customerapp.domain.dto.ProductDto;
import com.spring2020.customerapp.domain.entity.Category;
import com.spring2020.customerapp.domain.entity.Product;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-27T14:34:55+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_211 (Oracle Corporation)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setId( product.getId() );
        productDto.setName( product.getName() );
        productDto.setPrice( product.getPrice() );
        productDto.setDescription( product.getDescription() );
        productDto.setAvailable( product.isAvailable() );
        productDto.setCategory( categoryToCategoryDto( product.getCategory() ) );
        productDto.setUrl_img( product.getUrl_img() );

        return productDto;
    }

    @Override
    public Product toEntity(ProductDto product) {
        if ( product == null ) {
            return null;
        }

        Product product1 = new Product();

        product1.setId( product.getId() );
        product1.setName( product.getName() );
        product1.setPrice( product.getPrice() );
        product1.setDescription( product.getDescription() );
        product1.setAvailable( product.isAvailable() );
        product1.setCategory( categoryDtoToCategory( product.getCategory() ) );
        product1.setUrl_img( product.getUrl_img() );

        return product1;
    }

    protected CategoryDto categoryToCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId( category.getId() );
        categoryDto.setName( category.getName() );

        return categoryDto;
    }

    protected Category categoryDtoToCategory(CategoryDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryDto.getId() );
        category.setName( categoryDto.getName() );

        return category;
    }
}
