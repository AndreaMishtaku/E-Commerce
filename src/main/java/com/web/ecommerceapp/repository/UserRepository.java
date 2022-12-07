package com.web.ecommerceapp.repository;

import com.web.ecommerceapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmailOrUsername(String email,String username);


    User getUserByEmailOrUsername(String email,String username);

    User getUserByEmail(String email);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    @Query("select u,r from User u join u.roles r where r.name=:roleName")
    List<User> getUserByRole(@Param("roleName") String roleName);

    List<User> getUserByManagerId(Long id);

    @Query("select u,r from User u join u.roles r where r.name like 'ROLE_MANAGER' and u.id=:id")
    Optional<User> findManagerById(@Param("id") Long id);

    @Query("select u,r from User u join u.roles r where r.name like 'ROLE_OPERATOR' and u.id=:id")
    Optional<User> findOperatorById(@Param("id") Long id);

}
