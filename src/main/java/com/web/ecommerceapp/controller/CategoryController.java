package com.web.ecommerceapp.controller;

import com.web.ecommerceapp.payload.category.CategoryRequestDto;
import com.web.ecommerceapp.payload.category.CategoryResponseDto;
import com.web.ecommerceapp.payload.response.ActionSuccessful;
import com.web.ecommerceapp.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Category Controller", tags = {"Category"},description = "Category Controller")
@SwaggerDefinition(tags = {@Tag(name = "Category") })
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<ActionSuccessful> createCategory(@RequestBody CategoryRequestDto categoryRequestDto){
        return new ResponseEntity<>(categoryService.createCategory(categoryRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getProductById(@PathVariable(name="id") Long id){
        return new ResponseEntity<>(categoryService.getCategoryById(id),HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ActionSuccessful> updateCategory(@RequestBody CategoryRequestDto categoryRequestDto,@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(categoryService.updateCategory(categoryRequestDto,id),HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ActionSuccessful> deleteCategory(@PathVariable(name="id") Long id){
        return new ResponseEntity<>(categoryService.deleteCategoryById(id),HttpStatus.OK);


    }
}
