package io.joca.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.joca.rest.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}