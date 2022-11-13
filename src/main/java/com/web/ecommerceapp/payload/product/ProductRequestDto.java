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
    private String base64Image;
    private Double price;

    private Long categoryId;

    @JsonIgnore
    private Discount discount=null;


}
