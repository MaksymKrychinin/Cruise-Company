package com.example.cruiseonspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterFieldsDto {
    private String fieldName;
    private String fieldType;
}
