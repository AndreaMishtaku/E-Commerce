package com.web.ecommerceapp.payload.order;
import lombok.Data;


import java.util.List;

@Data
public class OrderResponseDto {
    private Long id;
    private String description;
    private Double amount;
    private String type;
    private Long userId;
    private List<OrderProductResponseDto> orderProducts;
    private Long locationId;
    private String bankCardCode;
    private String created_at;
    private String updated_at;
}
