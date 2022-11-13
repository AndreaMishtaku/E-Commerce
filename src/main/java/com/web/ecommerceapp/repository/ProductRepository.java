package com.web.ecommerceapp.repository;

import com.web.ecommerceapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
