package com.example.cruiseonspring.repository.specification;

import com.example.cruiseonspring.dto.SpecificationTransferDto;
import com.example.cruiseonspring.entity.UserOrder;
import org.springframework.stereotype.Component;

@Component
public class BaseSpecificationFactory<T> {
    public BaseFilterSpecification<T> specificationColumnFilter(SpecificationTransferDto specificationTransferDto){
        return new BaseFilterSpecification<>(specificationTransferDto.getFilterColumn(), specificationTransferDto.getFilterData());
    }
    public BaseFilterSpecification<UserOrder> specificationUserDetailsColumnFilter(String email){
        return new BaseFilterSpecification<>("user.email", email);
    }

}
