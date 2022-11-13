package com.web.ecommerceapp.repository;

import com.web.ecommerceapp.entity.Product;
import com.web.ecommerceapp.entity.ProductLocation;
import com.web.ecommerceapp.entity.embeddable.ProductLocationId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductLocationRepository extends JpaRepository<ProductLocation, ProductLocationId> {
    List<ProductLocation> findByLocation_id(Long id);
    ProductLocation findByLocation_idAndProduct_id(Long location_id,Long product_id);
}
