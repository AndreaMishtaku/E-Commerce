package com.web.ecommerceapp.payload.product;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private String productImage;
    private Double price;
    private Long categoryId;
    private Long dicountId;

    private String created_at;
    private String updated_at;

}
