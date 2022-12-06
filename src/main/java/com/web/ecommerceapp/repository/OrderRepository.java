package com.web.ecommerceapp.repository;

import com.web.ecommerceapp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> getOrdersByUserId(Long id);
    @Query("select o from Order o where o.user is null ")
    List<Order> getOnlineOrders();


    @Query("select o,u,m from Order o join o.user u join u.operatorSettingOperator m where m.manager.id=:id")
    List<Order> getOrdersForManager( @Param("id")Long id);
}
