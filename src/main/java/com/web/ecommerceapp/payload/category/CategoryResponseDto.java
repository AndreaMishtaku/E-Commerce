package com.web.ecommerceapp.payload.category;

import lombok.Data;

import java.util.UUID;

@Data
public class CategoryResponseDto {
    private Long id;
    private String name;
    private String description;
    private String created_at;
    private String updated_at;

}
