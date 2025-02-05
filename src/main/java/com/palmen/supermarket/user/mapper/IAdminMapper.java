package com.palmen.supermarket.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.palmen.supermarket.user.dto.RegisterUserDTO;
import com.palmen.supermarket.user.persistence.entity.Admin;

@Mapper(componentModel = "spring")
public interface IAdminMapper {

	@Mapping(target = "id", ignore = true) // Ignorar 'id' ya que es autoincremental
	@Mapping(target = "prueba", ignore = true) // Ignorar 'prueba' si no se requiere
	@Mapping(target = "role", ignore = true)
	Admin registerUserDTOToAdmin(RegisterUserDTO registerUserDTO);
}
