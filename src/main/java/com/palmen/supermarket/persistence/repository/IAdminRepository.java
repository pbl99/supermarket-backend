package com.palmen.supermarket.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.palmen.supermarket.persistence.entity.Admin;

@Repository
public interface IAdminRepository extends JpaRepository<Admin, Long> {
	Optional<Admin> findByUsername(String username);

	boolean existsByUsername(String username);
}
