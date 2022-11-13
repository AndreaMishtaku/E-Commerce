package com.web.ecommerceapp.payload.order;

import lombok.Data;

@Data
public class OrderProductRequestDto {
    private Integer quantity;
    Long productId;
}
