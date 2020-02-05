package com.spring2020.coffeeshop.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring2020.coffeeshop.domain.dto.ProductDto;
import com.spring2020.coffeeshop.domain.entity.Product;
import com.spring2020.coffeeshop.domain.entity.ProductImage;
import com.spring2020.coffeeshop.exception.MissingInputException;
import com.spring2020.coffeeshop.exception.ResourceNotFoundException;
import com.spring2020.coffeeshop.repository.ProductRepository;
import com.spring2020.coffeeshop.service.ProductService;
import com.spring2020.coffeeshop.util.FileAccessUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static com.spring2020.coffeeshop.util.ConstantUtil.IMAGE_DIRECTORY;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper mapper;

    private static final String DEFAULT_IMG = "default.jpg";

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        if (productDto == null) {
            throw new MissingInputException("missing input");
        }
        Product product = mapper.convertValue(productDto, Product.class);
        product.setAvailable(true);
        ProductImage productImage = new ProductImage();
        productImage.setId(1L);
        productImage.setImgUrl(DEFAULT_IMG);
        product.setProductImage(productImage);
        Product savedProduct = productRepository.save(product);
        return mapper.convertValue(savedProduct, ProductDto.class);
    }

    @Override
    public void updateProductData(long id, ProductDto productDto) {
        if (productDto == null) {
            throw new MissingInputException("missing input");
        }
        Product updatingProduct = findProductByIdReturnProduct(id);
        Product inputProduct = mapper.convertValue(productDto, Product.class);
        updatingProduct.setName(inputProduct.getName());
        updatingProduct.setPrice(inputProduct.getPrice());
        updatingProduct.setDescription(inputProduct.getDescription());
        updatingProduct.setCategory(inputProduct.getCategory());
        updatingProduct.setAvailable(inputProduct.isAvailable());
        productRepository.save(updatingProduct);
    }

    @Override
    public void updateProductImage(long id, MultipartFile file) {
        if (file == null) {
            throw new MissingInputException("missing input");
        }
        Product updatingProduct = findProductByIdReturnProduct(id);
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        File destFile = FileAccessUtil.createFile(IMAGE_DIRECTORY + fileName);
        FileAccessUtil.copyFile(destFile, file);
        if (updatingProduct.getProductImage().getImgUrl().equals(DEFAULT_IMG)) {
            ProductImage productImage = new ProductImage();
            productImage.setImgUrl(fileName);
            updatingProduct.setProductImage(productImage);
        } else {
            updatingProduct.getProductImage().setImgUrl(fileName);
        }
        productRepository.save(updatingProduct);
    }

    @Override
    public ProductDto findProductDataById(long id) {
        return mapper.convertValue(findProductByIdReturnProduct(id), ProductDto.class);
    }

    @Override
    public Resource findProductImageById(long id) {
        String imageURL = findProductByIdReturnProduct(id).getProductImage().getImgUrl();
        File file = new File(IMAGE_DIRECTORY + imageURL);
        return new FileSystemResource(file);
    }

    @Override
    public Page<ProductDto> findAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(product -> mapper.convertValue(product, ProductDto.class));
    }

    @Override
    public Page<ProductDto> findProductByNameOrCategory(String name, long categoryId, Pageable pageable) {
        return null;
    }


    private Product findProductByIdReturnProduct(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " not found"));
    }
}
