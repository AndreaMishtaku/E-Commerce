package com.web.ecommerceapp.repository;

import com.web.ecommerceapp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> getOrdersByUserId(Long id);
}
