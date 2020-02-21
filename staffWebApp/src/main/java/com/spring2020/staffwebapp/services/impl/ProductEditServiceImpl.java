package com.spring2020.staffwebapp.services.impl;

import com.spring2020.staffwebapp.domain.dto.DbResponseDto;
import com.spring2020.staffwebapp.domain.entity.Product;
import com.spring2020.staffwebapp.domain.enums.DbMessageEnum;
import com.spring2020.staffwebapp.domain.enums.DbStatusEnum;
import com.spring2020.staffwebapp.domain.enums.InputValidateMessageEnum;
import com.spring2020.staffwebapp.repositories.ProductRepository;
import com.spring2020.staffwebapp.services.ProductEditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductEditServiceImpl implements ProductEditService
{
    @Autowired
    ProductRepository productRepository;

    @Override
    public DbResponseDto editProductAvailability(long id, boolean isAvailable)
    {
        /*Set return database not used*/
        DbResponseDto dbResponseDto = new DbResponseDto();
        dbResponseDto.setDbStatus(DbStatusEnum.PENDING.getCode());
        dbResponseDto.setDbMessage(DbMessageEnum.PENDING.getMessage());
        dbResponseDto.setReason(DbMessageEnum.PENDING.getMessage());
        /*==============*/

//        Get editing product
        Optional<Product> productOptional = productRepository.findById(id);
//        Check if product is valid
        if (productOptional.isPresent())
        {
//            Edit availability
            Product product = productOptional.get();
            product.setAvailable(isAvailable);
//            Update product to db
            try
            {
                productRepository.save(product);
                dbResponseDto.setDbStatus(DbStatusEnum.SUCCESS.getCode());
                dbResponseDto.setDbMessage(DbMessageEnum.SUCCESS.getMessage());
                dbResponseDto.setReason(DbMessageEnum.SUCCESS.getMessage());
            } catch (Exception e)
            {
                dbResponseDto.setDbStatus(DbStatusEnum.FAILED.getCode());
                dbResponseDto.setDbMessage(DbMessageEnum.FAILED.getMessage());
                dbResponseDto.setReason(e.getMessage());
            }
        } else
        {
            dbResponseDto.setReason(InputValidateMessageEnum.PRODUCT_NOT_FOUND.getMessage());
        }
        return dbResponseDto;
    }
}
