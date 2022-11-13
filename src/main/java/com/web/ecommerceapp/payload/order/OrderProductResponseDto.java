package com.web.ecommerceapp.payload.order;

import lombok.Data;

@Data
public class OrderProductResponseDto {
    private Integer quantity;
    private String productName;
}