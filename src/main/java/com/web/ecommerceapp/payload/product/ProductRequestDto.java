package com.web.ecommerceapp.payload.product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.ecommerceapp.entity.Category;
import com.web.ecommerceapp.entity.Discount;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;


@Data
public class ProductRequestDto{
    private String name;
    private String description;
    private Double price;
    @JsonIgnore
    private String productImage;
    private Long categoryId;

    public ProductRequestDto(ProductResponseDto p) {
        this.setName(p.getName());
        this.setDescription(p.getDescription());
        this.setPrice(p.getPrice());
        this.setCategoryId(p.getCategoryId());
        this.setProductImage(p.getProductImage());
    }
}
