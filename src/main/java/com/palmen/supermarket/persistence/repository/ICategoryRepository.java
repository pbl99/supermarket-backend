package com.palmen.supermarket.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.palmen.supermarket.persistence.entity.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

	Optional<Category> findByName(String name);
}
