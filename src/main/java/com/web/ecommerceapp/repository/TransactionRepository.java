package com.web.ecommerceapp.repository;

import com.web.ecommerceapp.entity.Transaction;
import com.web.ecommerceapp.utils.PaginationSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TransactionRepository extends JpaRepository<Transaction,Long>, JpaSpecificationExecutor<Transaction> {
}
