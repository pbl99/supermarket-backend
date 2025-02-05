package com.palmen.supermarket.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.palmen.supermarket.user.dto.RegisterUserDTO;
import com.palmen.supermarket.user.persistence.entity.Employee;

@Mapper(componentModel = "spring")
public interface IEmployeeMapper {

	@Mapping(target = "role", ignore = true)
	Employee registerUserDTOToEmployee(RegisterUserDTO registerUserDTO);
}
