package com.palmen.supermarket.user.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.palmen.supermarket.user.dto.RegisterUserDTO;
import com.palmen.supermarket.user.mapper.IAdminMapper;
import com.palmen.supermarket.user.persistence.entity.Admin;
import com.palmen.supermarket.user.persistence.entity.Role;
import com.palmen.supermarket.user.persistence.repository.IAdminRepository;
import com.palmen.supermarket.user.persistence.repository.IRoleRepository;
import com.palmen.supermarket.user.service.IAdminService;
import com.palmen.supermarket.user.service.IPasswordService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements IAdminService {

	private final IAdminRepository adminRepository;
	private final IRoleRepository roleRepository;
	private final IAdminMapper adminMapper;
	private final IPasswordService passwordService;

	@Transactional
    @Override
    public Boolean createAdmin(RegisterUserDTO registerUserDTO) {
        // Resolver el Role basado en roleName
        Role role = roleRepository.findByName(registerUserDTO.getRole())
                .orElseThrow(() -> new RuntimeException("Role not found: " + registerUserDTO.getRole()));

        // Convertir el DTO en entidad y asignar el Role
        Admin admin = adminMapper.registerUserDTOToAdmin(registerUserDTO);
        admin.setRole(role);
        admin.setPassword(passwordService.encodePassword(admin.getPassword()));

        return adminRepository.save(admin) != null;
    }
}
