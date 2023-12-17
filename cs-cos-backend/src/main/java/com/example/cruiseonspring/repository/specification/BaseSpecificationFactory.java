package com.example.cruiseonspring.repository.specification;

import com.example.cruiseonspring.dto.SpecificationTransferDto;
import com.example.cruiseonspring.entity.UserOrder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BaseSpecificationFactory<T> {
    public BaseFilterSpecification<T> specificationColumnFilter(SpecificationTransferDto specificationTransferDto) {
        return new BaseFilterSpecification<>(specificationTransferDto.getFilterColumn(), specificationTransferDto.getFilterData());
    }

    public Specification<T> specificationColumnFilter(SpecificationTransferDto... specificationTransferDto) {
        Specification<T> first = new BaseFilterSpecification<>(specificationTransferDto[0].getFilterColumn(), specificationTransferDto[0].getFilterData());
        for (var spec : specificationTransferDto) {
            first = first.and(new BaseFilterSpecification<>(spec.getFilterColumn(), spec.getFilterData()));
        }
        return first;
    }

    public Specification<T> specificationColumnFilter(List<SpecificationTransferDto> specificationTransferDto) {
        Specification<T> first = new BaseFilterSpecification<>(specificationTransferDto.get(0).getFilterColumn(),
                specificationTransferDto.get(0).getFilterData());
        for (var spec : specificationTransferDto) {
            first = first.and(new BaseFilterSpecification<>(spec.getFilterColumn(), spec.getFilterData()));
        }
        return first;
    }

    public BaseFilterSpecification<UserOrder> specificationUserDetailsColumnFilter(String email) {
        return new BaseFilterSpecification<>("user.email", email);
    }

}
