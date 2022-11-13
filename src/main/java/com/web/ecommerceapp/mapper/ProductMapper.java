package com.web.ecommerceapp.mapper;




import com.web.ecommerceapp.entity.Product;

import com.web.ecommerceapp.entity.ProductLocation;
import com.web.ecommerceapp.payload.product.ProductRequestDto;
import com.web.ecommerceapp.payload.product.ProductResponseDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;


import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring",uses = {ReferenceMapper.class})
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    List<ProductResponseDto> productToDto(List<Product> products);

    @Mapping(source = "categoryId",target = "category")
    Product dtoToProduct(ProductRequestDto product);

    @Mapping(source = "category",target = "categoryId")
    ProductResponseDto productToDto(Product product);

    @Mapping(source = "categoryId",target = "category")
    void update( ProductRequestDto productDto,@MappingTarget Product product);

}



