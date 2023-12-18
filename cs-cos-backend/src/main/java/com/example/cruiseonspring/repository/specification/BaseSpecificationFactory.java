package com.example.cruiseonspring.repository.specification;

import com.example.cruiseonspring.dto.SpecificationTransferDto;
import com.example.cruiseonspring.entity.UserOrder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BaseSpecificationFactory<T> {
    public BaseFilterSpecification<T> specificationColumnFilter(SpecificationTransferDto specificationTransferDto) {
        return new BaseFilterSpecification<>(specificationTransferDto.getFieldName(), specificationTransferDto.getFieldData());
    }

    public Specification<T> specificationColumnFilter(SpecificationTransferDto... specificationTransferDto) {
        Specification<T> first = new BaseFilterSpecification<>(specificationTransferDto[0].getFieldName(), specificationTransferDto[0].getFieldData());
        for (var spec : specificationTransferDto) {
            first = first.and(new BaseFilterSpecification<>(spec.getFieldName(), spec.getFieldData()));
        }
        return first;
    }

    public Specification<T> specificationColumnFilter(List<SpecificationTransferDto> specificationTransferDto) {
        Specification<T> first = new BaseFilterSpecification<>(specificationTransferDto.get(0).getFieldName(),
                specificationTransferDto.get(0).getFieldData());
        for (var spec : specificationTransferDto) {
            first = first.and(new BaseFilterSpecification<>(spec.getFieldName(), spec.getFieldData()));
        }
        return first;
    }

    public BaseFilterSpecification<UserOrder> specificationUserDetailsColumnFilter(String email) {
        return new BaseFilterSpecification<>("user.email", email);
    }

}
