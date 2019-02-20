package io.joca.rest.service;

import java.util.List;

import io.joca.rest.api.v1.model.VendorDTO;
import io.joca.rest.api.v1.model.VendorListDTO;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 19, 2019
 *
 */
public interface VendorService {
	VendorListDTO getAllVendors();
	VendorDTO getVendorrById(Long id);
	VendorDTO createNewVendor(VendorDTO vendorDTO);
	VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO);
	VendorDTO patchVendor(Long id, VendorDTO vendorDTO);
	void deleteVendorById(Long id);
}