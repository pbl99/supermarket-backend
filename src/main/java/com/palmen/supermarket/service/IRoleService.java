package com.palmen.supermarket.service;

import java.util.List;

import com.palmen.supermarket.dto.RoleDTO;

public interface IRoleService {

	Boolean createRole(RoleDTO roleDTO);

	RoleDTO updateRole(RoleDTO roleDTO);

	Boolean deleteRole(Long id);

	List<RoleDTO> findAllRoles();
}
