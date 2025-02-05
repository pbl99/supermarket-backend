package com.palmen.supermarket.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.palmen.supermarket.user.dto.RoleDTO;
import com.palmen.supermarket.user.persistence.entity.Role;

@Mapper(componentModel = "spring")
public interface IRoleMapper {

	Role roleDTOToRole(RoleDTO roleDTO);

	RoleDTO roleToRoleDTO(Role role);
}
