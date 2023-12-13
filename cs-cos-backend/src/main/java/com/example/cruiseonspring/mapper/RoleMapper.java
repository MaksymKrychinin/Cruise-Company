package com.example.cruiseonspring.mapper;

import com.example.cruiseonspring.dto.RoleDto;
import com.example.cruiseonspring.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto roleToDto(Role source);
}
