package com.web.ecommerceapp.controller;


import com.web.ecommerceapp.payload.product.ProductRequestDto;
import com.web.ecommerceapp.payload.product.ProductResponseDto;
import com.web.ecommerceapp.payload.response.ActionSuccessful;
import com.web.ecommerceapp.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(value = "Product Controller", tags = {"Product"},description = "Product Controller")
@SwaggerDefinition(tags = {@Tag(name = "Product") })
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping
    public ResponseEntity<ActionSuccessful> createProduct(@RequestBody ProductRequestDto productRequestDto){
        return new ResponseEntity<>(productService.createProduct(productRequestDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get all products")
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get product by id")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable(name="id") Long id){
        return new ResponseEntity<>(productService.getProductById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update product")
    public ResponseEntity<ActionSuccessful> updateProduct(@RequestBody ProductRequestDto productRequestDto,@PathVariable(name="id") Long id){
        return new ResponseEntity<>(productService.updateProduct(productRequestDto,id),HttpStatus.OK);
    }

    @ApiOperation(value = "Delete product")
    @DeleteMapping("/{id}")
    public ResponseEntity<ActionSuccessful> deleteProduct(@PathVariable(name="id") Long id){
        return new ResponseEntity<>(productService.deleteProductById(id),HttpStatus.OK);
    }
}
