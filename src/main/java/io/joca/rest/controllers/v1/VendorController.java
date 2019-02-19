package io.joca.rest.controllers.v1;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.joca.rest.api.v1.model.VendorDTO;
import io.joca.rest.api.v1.model.VendorListDTO;
import io.joca.rest.service.VendorService;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 19, 2019
 *
 */
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

	public static final String BASE_URL = "/api/v1/vendors";
	
	private final VendorService vendorService;

	public VendorController(VendorService vendorService) {
		this.vendorService = vendorService;
	}
	
	@GetMapping
	public VendorListDTO getAllVendors() {
		return new VendorListDTO(vendorService.getAllVendors());
	}
}