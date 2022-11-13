package com.web.ecommerceapp.payload.location;

import lombok.Data;

@Data
public class ProductLocationRequestDto {
    public Long productId;
    private Integer stock;
}
