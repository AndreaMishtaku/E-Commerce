package com.web.ecommerceapp.entity.embeddable;

import com.web.ecommerceapp.entity.Location;
import com.web.ecommerceapp.entity.Product;
import lombok.*;

import javax.persistence.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProductLocationId implements Serializable {

    @Column(name = "location_id", nullable = false)
    private Long locationId;
    @Column(name="product_id",nullable = false)
    private Long productId;
}