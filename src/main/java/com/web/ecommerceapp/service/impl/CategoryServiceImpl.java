package com.web.ecommerceapp.service.impl;
import com.web.ecommerceapp.entity.Category;
import com.web.ecommerceapp.exception.ResourceNotFoundException;
import com.web.ecommerceapp.mapper.CategoryMapper;
import com.web.ecommerceapp.payload.category.CategoryRequestDto;
import com.web.ecommerceapp.payload.category.CategoryResponseDto;
import com.web.ecommerceapp.payload.response.ActionSuccessful;
import com.web.ecommerceapp.repository.CategoryRepository;
import com.web.ecommerceapp.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;


    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public ActionSuccessful createCategory(CategoryRequestDto categoryDto) {
        Category category =categoryMapper.dtoToCategory(categoryDto);
        categoryRepository.save(category);
        return new ActionSuccessful(true,"Category created successfully");
    }
    @Override
    public List<CategoryResponseDto> getAllCategories() {

        List<Category> categories=categoryRepository.findAll();
        List<CategoryResponseDto> categoryResponseDtoList=categoryMapper.categoryToDto(categories);
        return categoryResponseDtoList;
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        Category category =categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category","id",id));
        CategoryResponseDto categoryResponseDto=categoryMapper.categoryToDto(category);
        return categoryResponseDto;
    }

    @Override
    public ActionSuccessful updateCategory(CategoryRequestDto categoryDto, Long id) {
        Category category=categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category","id",id));
        categoryMapper.update(categoryDto,category);
        categoryRepository.save(category);
        return new ActionSuccessful(true,"Category updated successfully");
    }

    @Override
    public ActionSuccessful deleteCategoryById(Long id) {
        Category category=categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category","id",id));
        categoryRepository.delete(category);
        return new ActionSuccessful(true,"Category deleted successfully");
    }
}
