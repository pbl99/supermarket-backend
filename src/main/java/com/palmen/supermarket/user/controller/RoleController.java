package com.palmen.supermarket.user.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palmen.supermarket.user.dto.RoleDTO;
import com.palmen.supermarket.user.service.IRoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/role")
public class RoleController {

	private final IRoleService roleService;

	@PostMapping("/createRole")
	public ResponseEntity<?> createRole(@RequestBody RoleDTO roleDTO) {
		Boolean roleCreated = roleService.createRole(roleDTO);

		return roleCreated ? ResponseEntity.status(HttpStatus.CREATED).body(roleCreated)
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Role not created");
	}

	@PutMapping("/updateRole")
	public ResponseEntity<?> updateRole(@RequestBody RoleDTO roleDTO) {
		RoleDTO roleUpdated = roleService.updateRole(roleDTO);

		return roleUpdated != null ? ResponseEntity.status(HttpStatus.OK).body(roleUpdated)
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Role not updated");
	}

	@DeleteMapping("/deleteRole/{id}")
	public ResponseEntity<?> deleteRole(@PathVariable Long id) {
		Boolean roleDeleted = roleService.deleteRole(id);

		return roleDeleted ? ResponseEntity.status(HttpStatus.OK).body(roleDeleted)
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Role not deleted");
	}

	@GetMapping("/findAllRoles")
	public ResponseEntity<?> findAllRoles() {
		List<RoleDTO> roles = roleService.findAllRoles();

		return roles.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Roles not found")
				: ResponseEntity.status(HttpStatus.FOUND).body(roles);
	}
}
