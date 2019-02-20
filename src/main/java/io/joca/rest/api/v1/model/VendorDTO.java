package io.joca.rest.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 19, 2019
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorDTO {
	private String name;
	private String url;
}