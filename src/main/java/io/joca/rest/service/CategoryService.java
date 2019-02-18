package io.joca.rest.service;

import java.util.List;

import io.joca.rest.api.v1.model.CategoryDTO;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 18, 2019
 *
 */
public interface CategoryService {

	List<CategoryDTO> getAllCategories();
	CategoryDTO getCategoryByName(String name);
}