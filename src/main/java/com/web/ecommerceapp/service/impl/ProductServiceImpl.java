package com.web.ecommerceapp.service.impl;
import com.web.ecommerceapp.entity.Product;
import com.web.ecommerceapp.exception.ResourceNotFoundException;
import com.web.ecommerceapp.mapper.ProductMapper;
import com.web.ecommerceapp.payload.product.ProductRequestDto;
import com.web.ecommerceapp.payload.product.ProductResponseDto;
import com.web.ecommerceapp.payload.response.ActionSuccessful;
import com.web.ecommerceapp.repository.CategoryRepository;
import com.web.ecommerceapp.repository.ProductRepository;
import com.web.ecommerceapp.service.FileService;
import com.web.ecommerceapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service

public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;


    public ProductServiceImpl(ProductMapper productMapper, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;

    }

    @Override
    public ActionSuccessful createProduct(MultipartFile image,ProductRequestDto productDto)  {
        Product product=productMapper.dtoToProduct(productDto);

        try{
        product.setProductImage(fileService.uploadImage(path,image));
        }catch(IOException e){
            return new ActionSuccessful(false,"Error ne upload e imazhit");
        }

        productRepository.save(product);
        return new ActionSuccessful(true,"Shtimi i produktit u krye me sukses");
    }


    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products=productRepository.findAll();
        List<ProductResponseDto> productList=productMapper.productToDto(products);
        return productList;
    }



    @Override
    public ProductResponseDto getProductById(Long id) {
        Product product=productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product","id",id));
        ProductResponseDto productResponseDto=productMapper.productToDto(product);

        return productResponseDto;
    }

    @Override
    public ActionSuccessful updateProduct(ProductRequestDto productDto,Long id) {
        Product product=productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product","id",id));
        productMapper.update(productDto,product);
        productRepository.save(product);
        return new ActionSuccessful(true,"Product updated successfully");
    }

    @Override
    public ActionSuccessful deleteProductById(Long id) {
        Product product=productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product","id",id));
        productRepository.delete(product);
        return new ActionSuccessful(true,"Product deleted successfully");
    }
}
