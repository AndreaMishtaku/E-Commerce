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

    @MapsId("location_id")
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    @ManyToOne
    private Location location;

    @MapsId("product_id")
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne
    private Product product;

    private Integer stock;

}
