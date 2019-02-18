package io.joca.rest.api.v1.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 18, 2019
 *
 */
@Data
@AllArgsConstructor
public class CategoryListDTO {
	List<CategoryDTO> categories;
}