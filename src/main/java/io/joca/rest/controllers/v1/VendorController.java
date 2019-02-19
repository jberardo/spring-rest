package io.joca.rest.controllers.v1;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("{id}")
	public VendorDTO getVendorById(@PathVariable Long id) {
		return vendorService.getVendorrById(id);
	}
	
	@PostMapping()
	public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO) {
		return vendorService.createNewVendor(vendorDTO);
	}
	
	@PutMapping("{id}")
	public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
		return vendorService.saveVendorByDTO(id, vendorDTO);
	}
	
	@PatchMapping("{id}")
	public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
		return vendorService.patchVendor(id, vendorDTO);
	}
	
	@DeleteMapping("{id}")
	public void deleteVendor(@PathVariable Long id) {
		vendorService.deleteVendorById(id);
	}
}