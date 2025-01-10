package com.palmen.supermarket.persistence.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.palmen.supermarket.persistence.entity.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
	Optional<Customer> findByUsername(String username);

	boolean existsByUsername(String username);;
}
