package com.tccompany.tcuombackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpecificationTransferDto {
    private String filterColumn;
    private String filterData;
}
