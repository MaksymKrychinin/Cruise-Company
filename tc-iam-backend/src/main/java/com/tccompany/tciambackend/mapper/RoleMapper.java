package com.tccompany.tciambackend.mapper;

import com.tccompany.tciambackend.dto.RoleDto;
import com.tccompany.tciambackend.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto roleToDto(Role source);
}
