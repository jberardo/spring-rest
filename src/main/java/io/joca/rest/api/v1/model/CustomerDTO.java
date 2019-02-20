package io.joca.rest.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 18, 2019
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
	private Long id;
	private String firstname;
	private String lastname;
	private String url;
}