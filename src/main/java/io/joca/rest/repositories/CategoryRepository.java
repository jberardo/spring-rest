package io.joca.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.joca.rest.api.v1.model.Category;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 18, 2019
 *
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
	Category findByName(String name);
}