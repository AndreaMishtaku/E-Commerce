package com.web.ecommerceapp.controller;

import com.web.ecommerceapp.payload.category.CategoryRequestDto;
import com.web.ecommerceapp.payload.category.CategoryResponseDto;
import com.web.ecommerceapp.payload.response.ActionSuccessful;
import com.web.ecommerceapp.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Category Controller", tags = {"Category"},description = "Category Controller")
@SwaggerDefinition(tags = {@Tag(name = "Category") })
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;



    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Create Category ADMIN")
    @PostMapping
    public ResponseEntity<ActionSuccessful> createCategory(@RequestBody CategoryRequestDto categoryRequestDto){
        return new ResponseEntity<>(categoryService.createCategory(categoryRequestDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Get all categories")
    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Get category by id")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable(name="id") Long id){
        return new ResponseEntity<>(categoryService.getCategoryById(id),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Update category")
    @PutMapping("/{id}")
    public ResponseEntity<ActionSuccessful> updateCategory(@RequestBody CategoryRequestDto categoryRequestDto,@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(categoryService.updateCategory(categoryRequestDto,id),HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete Category")
    @DeleteMapping("/{id}")
    public ResponseEntity<ActionSuccessful> deleteCategory(@PathVariable(name="id") Long id){
        return new ResponseEntity<>(categoryService.deleteCategoryById(id),HttpStatus.OK);
    }
}
