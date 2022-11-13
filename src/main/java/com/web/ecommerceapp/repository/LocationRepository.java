package com.web.ecommerceapp.repository;

import com.web.ecommerceapp.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location,Long> {


    @Query(value = "SELECT *  FROM LOCATIONS INNER JOIN PRODUCT_LOCATION ON LOCATIONS.ID=PRODUCT_LOCATION.LOCATION_ID WHERE PRODUCT_LOCATION.PRODUCT_ID=:product_id ORDER BY STOCK DESC",nativeQuery = true)
    List<Location> getLocationByProductStockOrder(@Param("product_id") Long productId);
}
