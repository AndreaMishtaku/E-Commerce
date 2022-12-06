package com.web.ecommerceapp.controller;


import com.web.ecommerceapp.payload.product.ProductRequestDto;
import com.web.ecommerceapp.payload.product.ProductResponseDto;
import com.web.ecommerceapp.payload.response.ActionSuccessful;
import com.web.ecommerceapp.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Api(value = "Product Controller", tags = {"Product"},description = "Product Controller")
@SwaggerDefinition(tags = {@Tag(name = "Product") })
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ApiOperation(value = "Create Product")
    public ResponseEntity<ActionSuccessful> createProduct(@RequestBody ProductRequestDto productDto){
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
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

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @ApiOperation(value = "Update product")
    public ResponseEntity<ActionSuccessful> updateProduct(@RequestBody ProductRequestDto productRequestDto,@PathVariable(name="id") Long id){
        return new ResponseEntity<>(productService.updateProduct(productRequestDto,id),HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping( "/{productId}/image")
    @ApiOperation(value = "Upload product Image")
    public ResponseEntity<ActionSuccessful> uploadProductImage(@PathVariable(name = "productId") Long productId,@RequestParam("image")MultipartFile image) throws IOException {
        return new ResponseEntity<>(productService.uploadProductImage(image,productId),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete product")
    @DeleteMapping("/{id}")
    public ResponseEntity<ActionSuccessful> deleteProduct(@PathVariable(name="id") Long id){
        return new ResponseEntity<>(productService.deleteProductById(id),HttpStatus.OK);
    }
}
