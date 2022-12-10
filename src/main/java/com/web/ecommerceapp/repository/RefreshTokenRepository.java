package com.web.ecommerceapp.repository;

import com.web.ecommerceapp.entity.RefreshToken;
import com.web.ecommerceapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    Optional<RefreshToken> findByToken(String token);

    RefreshToken getByUser(User user);

    @Modifying
    int deleteByUser(User user);
}
