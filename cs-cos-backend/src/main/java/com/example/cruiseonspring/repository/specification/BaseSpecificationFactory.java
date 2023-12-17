package com.example.cruiseonspring.repository.specification;

import com.example.cruiseonspring.dto.SpecificationTransferDto;
import com.example.cruiseonspring.entity.UserOrder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class BaseSpecificationFactory<T> {
    public BaseFilterSpecification<T> specificationColumnFilter(SpecificationTransferDto specificationTransferDto){
        return new BaseFilterSpecification<>(specificationTransferDto.getFilterColumn(), specificationTransferDto.getFilterData());
    }
    public Specification<T> specificationColumnFilter(SpecificationTransferDto[] specificationTransferDto){
        Specification<T> first = new BaseFilterSpecification<T>(specificationTransferDto[0].getFilterColumn(), specificationTransferDto[0].getFilterData());
        for (var spec: specificationTransferDto){
            first = first.and(new BaseFilterSpecification<>(spec.getFilterColumn(), spec.getFilterData()));
        }
        return first;
    }
    public BaseFilterSpecification<UserOrder> specificationUserDetailsColumnFilter(String email){
        return new BaseFilterSpecification<>("user.email", email);
    }

}
