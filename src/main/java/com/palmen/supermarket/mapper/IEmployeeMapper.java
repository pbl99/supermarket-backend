package com.palmen.supermarket.mapper;

import org.mapstruct.Mapper;

import com.palmen.supermarket.dto.RegisterUserDTO;
import com.palmen.supermarket.persistence.entity.Employee;

@Mapper(componentModel = "spring")
public interface IEmployeeMapper {

	Employee registerUserDTOToEmployee(RegisterUserDTO registerUserDTO);
}
