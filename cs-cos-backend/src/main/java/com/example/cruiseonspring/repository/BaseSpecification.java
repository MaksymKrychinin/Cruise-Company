package com.example.cruiseonspring.repository;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class BaseSpecification<T> implements Specification<T> {

    private String filterColumn;
    private String filterData;
    private String[] joinTable;

    public BaseSpecification(String filterColumn, String filterData) {
        this.filterColumn = filterColumn;
        this.filterData = filterData;
    }

    public BaseSpecification(String filterColumn, String filterData, String... joinTable) {
        this.filterColumn = filterColumn;
        this.filterData = filterData;
        this.joinTable = joinTable;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (filterColumn != null) {
            if (joinTable != null) {
                Join<Object, Object> join = root.join(joinTable[0]);
                for (int i = 1; i < joinTable.length; i++) {
                    join = join.join(joinTable[i]);
                }
                return criteriaBuilder.like(join.get(filterColumn), "%" + filterData + "%");
            } else {
                return criteriaBuilder.like(root.get(filterColumn), "%" + filterData + "%");
            }
        }
        return null;
    }
}