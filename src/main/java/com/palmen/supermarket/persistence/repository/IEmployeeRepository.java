package com.palmen.supermarket.persistence.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.palmen.supermarket.persistence.entity.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
	Optional<Employee> findByUsername(String username);

	boolean existsByUsername(String username);
}
