package com.web.ecommerceapp.service;

import com.web.ecommerceapp.payload.product.ProductRequestDto;
import com.web.ecommerceapp.payload.product.ProductResponseDto;
import com.web.ecommerceapp.payload.response.ActionSuccessful;

import java.util.List;

public interface ProductService {

    ActionSuccessful createProduct(ProductRequestDto productDto);

    List<ProductResponseDto> getAllProducts();

    ProductResponseDto getProductById(Long id);

    ActionSuccessful updateProduct(ProductRequestDto productDto,Long id);

    ActionSuccessful deleteProductById(Long id);
}
