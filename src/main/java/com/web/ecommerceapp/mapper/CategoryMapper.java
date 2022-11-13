package com.web.ecommerceapp.mapper;


import com.web.ecommerceapp.entity.Category;
import com.web.ecommerceapp.payload.category.CategoryRequestDto;
import com.web.ecommerceapp.payload.category.CategoryResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    List<CategoryResponseDto> categoryToDto(List<Category> categories);
    Category dtoToCategory(CategoryRequestDto categoryDto);
    CategoryResponseDto categoryToDto(Category category);

    void update(CategoryRequestDto categoryDto, @MappingTarget Category category);
}
