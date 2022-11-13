package com.web.ecommerceapp.enums;

import com.web.ecommerceapp.payload.pagination.FilterDto;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

@Slf4j
public enum Operator {

    EQUAL {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterDto request, Predicate predicate) {
            String value = request.getValue();
            Expression<?> key = root.get(request.getColumnName());

            System.out.println(key);
            return cb.and(cb.equal(key, value), predicate);
        }
    },

    NOT_EQUAL {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterDto request, Predicate predicate) {
            String value = request.getValue();
            Expression<?> key = root.get(request.getColumnName());
            return cb.and(cb.notEqual(key, value), predicate);
        }
    },

    LIKE {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterDto request, Predicate predicate) {
            Expression<String> key = root.get(request.getColumnName());
            return cb.and(cb.like(cb.upper(key), "%" + request.getValue().toString().toUpperCase() + "%"), predicate);
        }
    },



    GREATER_THAN{
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterDto request, Predicate predicate) {
            String value = request.getValue();
            Expression<? extends String> key = root.get(request.getColumnName());
            return cb.and(cb.greaterThan(key,value), predicate);
        }
        },

    LESS_THAN{
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterDto request, Predicate predicate) {
            String value = request.getValue();
            Expression<? extends String> key = root.get(request.getColumnName());
            return cb.and(cb.lessThan(key,value), predicate);
        }};


        public abstract <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterDto request, Predicate predicate);

}
