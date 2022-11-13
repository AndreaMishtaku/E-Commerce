package com.web.ecommerceapp.enums;

import com.web.ecommerceapp.payload.pagination.SortingDto;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

public enum SortDirection {

    ASC {
        public <T> Order build(Root<T> root, CriteriaBuilder cb, SortingDto request) {
            return cb.asc(root.get(request.getColumName()));
        }
    },
    DESC {
        public <T> Order build(Root<T> root, CriteriaBuilder cb, SortingDto request) {
            return cb.desc(root.get(request.getColumName()));
        }
    };

    public abstract <T> Order build(Root<T> root, CriteriaBuilder cb, SortingDto request);

}
