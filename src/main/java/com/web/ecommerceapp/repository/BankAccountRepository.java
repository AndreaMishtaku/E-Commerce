package com.web.ecommerceapp.repository;

import com.web.ecommerceapp.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,Long> {
}
