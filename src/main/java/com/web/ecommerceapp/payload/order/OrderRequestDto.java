package com.web.ecommerceapp.payload.order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import java.util.List;

@Data
public class OrderRequestDto {
    private String description;
    private List<OrderProductRequestDto> orderProducts;
    @JsonIgnore
    private Double amount;
    private BankCardForOrderDto bankCard;

}

