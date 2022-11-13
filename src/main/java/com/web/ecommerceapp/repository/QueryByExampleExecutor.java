package com.web.ecommerceapp.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public interface QueryByExampleExecutor<T> {
    <S extends T> Optional<S> findOne(Example<S> var1);

    <S extends T> Iterable<S> findAll(Example<S> var1);

    <S extends T> Iterable<S> findAll(Example<S> var1, Sort var2);

    <S extends T> Page<S> findAll(Example<S> var1, Pageable var2);

    <S extends T> long count(Example<S> var1);

    <S extends T> boolean exists(Example<S> var1);
}
