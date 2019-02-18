package io.joca.rest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 18, 2019
 *
 */
@Data
public class CustomerDTO {
	private Long id;
	private String firstname;
	private String lastname;
	
	@JsonProperty("url")
	private String url;
}