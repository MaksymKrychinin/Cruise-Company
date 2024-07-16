package com.example.cruiseonspring.repository.specification;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class BaseFilterSpecification<T> implements Specification<T> {
    private final String filterColumn;
    private final String filterData;
    private String[] joinTable;

    public BaseFilterSpecification(String filterColumn, String filterData) {
        this.filterColumn = filterColumn;
        this.filterData = filterData;
    }

    /**
     * @see BaseFilterSpecification (String filterColumn, String filterData)
     * @deprecated Please now use the constructor without the joinTable parameter
     */
    @Deprecated
    public BaseFilterSpecification(String filterColumn, String filterData, @Deprecated String... joinTable) {
        this.filterColumn = filterColumn;
        this.filterData = filterData;
        this.joinTable = joinTable;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (filterColumn == null) {
            return null;
        }

        String[] split = filterColumn.split("\\.");

        if (split.length <= 1) {
            return criteriaBuilder.like(root.get(filterColumn), "%" + filterData + "%");
        } else {
            joinTable = split;
            Join<Object, Object> join = root.join(joinTable[0]);
            for (int i = 1; i < joinTable.length - 1; i++) {
                join = join.join(joinTable[i]);
            }
            return criteriaBuilder.like(join.get(split[split.length - 1]), "%" + filterData + "%");
        }
    }

}