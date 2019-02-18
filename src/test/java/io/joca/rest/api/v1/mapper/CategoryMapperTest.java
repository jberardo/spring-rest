package io.joca.rest.api.v1.mapper;

import org.junit.Test;

import io.joca.rest.api.v1.model.CategoryDTO;
import io.joca.rest.domain.Category;

import static org.junit.Assert.assertEquals;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 18, 2019
 *
 */
public class CategoryMapperTest {
	
    public static final long ID = 1L;
	public static final String NAME = "Joe";

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO() throws Exception {

        //given
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        //when
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        //then
        assertEquals(Long.valueOf(ID), categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }
}