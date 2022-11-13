package com.web.ecommerceapp.repository;

import com.web.ecommerceapp.entity.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankCardRepository extends JpaRepository<BankCard,Long> {
    Optional<BankCard> findByCode(String code);
}
