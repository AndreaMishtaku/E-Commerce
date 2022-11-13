package com.web.ecommerceapp.entity.embeddable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;

import java.io.Serializable;

@Embeddable
public class ProductLocationId implements Serializable {

    private Long location_id;

    private Long product_id;
}