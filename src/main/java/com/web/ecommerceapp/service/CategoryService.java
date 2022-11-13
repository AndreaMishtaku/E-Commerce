package com.web.ecommerceapp.service;

import com.web.ecommerceapp.payload.category.CategoryRequestDto;
import com.web.ecommerceapp.payload.category.CategoryResponseDto;

import com.web.ecommerceapp.payload.response.ActionSuccessful;

import java.util.List;

public interface CategoryService {
    ActionSuccessful createCategory(CategoryRequestDto categoryDto);

    List<CategoryResponseDto> getAllCategories();

    CategoryResponseDto getCategoryById(Long id);

    ActionSuccessful updateCategory(CategoryRequestDto categoryDto,Long id);

    ActionSuccessful deleteCategoryById(Long id);
}
