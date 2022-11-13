package com.web.ecommerceapp.repository;

import com.web.ecommerceapp.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount,Long> {
}
