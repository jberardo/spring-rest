package io.joca.rest.api.v1.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 17, 2019
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
	private Long id;
	private String name;
}