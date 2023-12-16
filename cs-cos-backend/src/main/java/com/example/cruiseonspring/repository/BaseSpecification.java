package com.example.cruiseonspring.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class BaseSpecification<T> implements Specification<T> {

    private String filterColumn;
    private String filterData;
    private String joinTable;

    public BaseSpecification(String filterColumn, String filterData) {
        this.filterColumn = filterColumn;
        this.filterData = filterData;
    }

    public BaseSpecification(String filterColumn, String filterData, String joinTable) {
        this.filterColumn = filterColumn;
        this.filterData = filterData;
        this.joinTable = joinTable;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (filterColumn != null) {
            if (joinTable != null) {
                return criteriaBuilder.like(root.join(joinTable).get(filterColumn), "%" + filterData + "%");
            } else {
                return criteriaBuilder.like(root.get(filterColumn), "%" + filterData + "%");
            }
        }
        return null;
    }
}