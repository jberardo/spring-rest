package io.joca.rest.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
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
	
	@ApiModelProperty(value = "Customer first name.", required = true)
	private String firstname;
	
	@ApiModelProperty(value = "Customer last name.", required = false)
	private String lastname;
	
	private String url;
}