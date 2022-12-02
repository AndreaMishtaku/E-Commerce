package com.web.ecommerceapp.entity;

import com.web.ecommerceapp.entity.embeddable.ProductLocationId;
import lombok.*;

import javax.persistence.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="product_location")

public class ProductLocation {

    @EmbeddedId
    private ProductLocationId productLocationId=new ProductLocationId();
    
    @MapsId("locationId")
    @ManyToOne
    private Location location;

    @MapsId("productId")
    @ManyToOne
    private Product product;

    private Integer stock;


}
