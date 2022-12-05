package com.web.ecommerceapp.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.ecommerceapp.entity.Product;
import com.web.ecommerceapp.payload.product.ProductRequestDto;
import com.web.ecommerceapp.payload.product.ProductResponseDto;
import com.web.ecommerceapp.payload.response.ActionSuccessful;
import com.web.ecommerceapp.service.FileService;
import com.web.ecommerceapp.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
@Api(value = "Product Controller", tags = {"Product"},description = "Product Controller")
@SwaggerDefinition(tags = {@Tag(name = "Product") })
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private FileService fileService;

    private ObjectMapper mapper;

    @Value("${project.image}")
    private String path;

    @PostMapping
    public ResponseEntity<ActionSuccessful> createProduct(
            @RequestParam("file") MultipartFile file,
            @RequestParam("userData") String productData
    ){


        ProductRequestDto productDto;
        try {
          productDto = mapper.readValue(productData, ProductRequestDto.class);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e.getMessage());
        }

        return new ResponseEntity<>(productService.createProduct(file,productDto), HttpStatus.CREATED);
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

    @PostMapping("/{productId}/image")
    public ResponseEntity<ActionSuccessful> uploadProductImage(@PathVariable(name = "productId") Long productId,@RequestParam("image")MultipartFile image) throws IOException {

        String fileName=fileService.uploadImage(path,image);
        ProductResponseDto p=productService.getProductById(productId);
        p.setProductImage(fileName);
        ProductRequestDto updated=new ProductRequestDto(p);

        productService.updateProduct(updated,productId);
        return new ResponseEntity<>(new ActionSuccessful(true,"Image uploaded successfully"),HttpStatus.OK);
    }


    @GetMapping(value = "/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable(name = "imageName") String imageName, HttpServletResponse response)throws IOException{
        InputStream resource=this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());

    }

    @ApiOperation(value = "Delete product")
    @DeleteMapping("/{id}")
    public ResponseEntity<ActionSuccessful> deleteProduct(@PathVariable(name="id") Long id){
        return new ResponseEntity<>(productService.deleteProductById(id),HttpStatus.OK);
    }
}
