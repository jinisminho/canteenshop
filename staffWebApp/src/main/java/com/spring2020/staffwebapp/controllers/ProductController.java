package com.spring2020.staffwebapp.controllers;

import com.spring2020.staffwebapp.domain.dto.DbResponseDto;
import com.spring2020.staffwebapp.domain.dto.ProductDto;
import com.spring2020.staffwebapp.services.ProductEditService;
import com.spring2020.staffwebapp.services.ProductRetrieveService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController
{

    @Autowired
    private ProductRetrieveService productRetrieveService;
    @Autowired
    private ProductEditService productEditService;

    @GetMapping("/all")
    public Page<ProductDto> showAllAvailableProducts(Pageable pageable)
    {
        return productRetrieveService.findAllProducts(pageable);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/details")
    public ProductDto showProductDetails(@RequestParam(value = "ProductId") long id)
    {
        return productRetrieveService.showProductDetails(id);
    }

    @GetMapping("/search")
    @ApiOperation(value = "Will search based on what is entered")
    public Page<ProductDto> searchProducts(@RequestParam(value = "ProductName", required = false) String name,
                                           @RequestParam(value = "Category", required = false) Integer categoryId,
                                           @RequestParam(value = "Availability") @ApiParam(example = "true", required = true, value = "Status") Boolean isAvailable,
                                           Pageable pageable)
    {
        return productRetrieveService.findProducts(pageable, name, categoryId, isAvailable);
    }

    @PostMapping("/update")
    @ApiOperation(value = "Update product availability")
    public DbResponseDto editProductAvailability(@RequestParam(value = "ProductId") long id, @RequestParam(value = "isAvailable") boolean isAvailable)
    {
        return productEditService.editProductAvailability(id, isAvailable);
    }

}