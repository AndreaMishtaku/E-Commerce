package com.web.ecommerceapp.payload.location;
import com.web.ecommerceapp.entity.embeddable.Address;
import lombok.Data;

@Data
public class LocationRequestDto {
    private String name;
    private Address address;
    private Long userId;
}
