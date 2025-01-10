package com.palmen.supermarket.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.palmen.supermarket.dto.RoleDTO;
import com.palmen.supermarket.mapper.IRoleMapper;
import com.palmen.supermarket.persistence.entity.Role;
import com.palmen.supermarket.persistence.repository.IRoleRepository;
import com.palmen.supermarket.service.IRoleService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {

	private final IRoleRepository roleRepository;
	private final IRoleMapper roleMapper;
	
	@Transactional
	@Override
	public Boolean createRole(RoleDTO roleDTO) {
		return roleRepository.save(roleMapper.roleDTOToRole(roleDTO)) != null;
	}
	
	@Transactional
	@Override
	public RoleDTO updateRole(RoleDTO roleDTO) {
		Role roleExist = roleRepository.findById(roleDTO.getId())
				.orElseThrow();
		
		Role roleUpdated = Role.builder()
				.name(roleExist.getName())
				.build();
		
		roleRepository.save(roleUpdated);
		return roleMapper.roleToRoleDTO(roleUpdated);
	}
	
	@Transactional
	@Override
	public Boolean deleteRole(Long id) {
		if(roleRepository.existsById(id)) {
			roleRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<RoleDTO> findAllRoles(){
		return roleRepository.findAll()
				.stream()
				.map(roleMapper::roleToRoleDTO)
				.collect(Collectors.toList());
	}
}
