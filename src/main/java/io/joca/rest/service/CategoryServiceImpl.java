package io.joca.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.joca.rest.api.v1.mapper.CategoryMapper;
import io.joca.rest.api.v1.model.CategoryDTO;
import io.joca.rest.repositories.CategoryRepository;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 18, 2019
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;
	private final CategoryMapper mapper;
	
	public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper mapper) {
		this.categoryRepository = categoryRepository;
		this.mapper = mapper;
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		return categoryRepository.findAll()
			.stream()
			.map(mapper::categoryToCategoryDTO)
			.collect(Collectors.toList());
	}

	@Override
	public CategoryDTO getCategoryByName(String name) {
		return mapper.categoryToCategoryDTO(categoryRepository.findByName(name));
	}
}