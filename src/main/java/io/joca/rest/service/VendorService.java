package io.joca.rest.service;

import java.util.List;

import io.joca.rest.api.v1.model.VendorDTO;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 19, 2019
 *
 */
public interface VendorService {
	List<VendorDTO> getAllVendors();
}