package com.web.ecommerceapp.payload.location;

import com.web.ecommerceapp.entity.embeddable.Address;
import lombok.Data;


@Data
public class LocationResponseDto {
    private Long id;
    private String name;
    private Address address;
    private String created_at;
    private String updated_at;
    private Long userId;
}
