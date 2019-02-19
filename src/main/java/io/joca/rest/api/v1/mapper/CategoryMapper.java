package io.joca.rest.api.v1.mapper;

import io.joca.rest.api.v1.model.Category;
import io.joca.rest.api.v1.model.CategoryDTO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 17, 2019
 *
 */
@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}