package io.joca.rest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.joca.rest.api.v1.mapper.VendorMapper;
import io.joca.rest.api.v1.model.VendorDTO;
import io.joca.rest.controllers.v1.VendorController;
import io.joca.rest.repositories.VendorRepository;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 19, 2019
 *
 */
@Service
public class VendorServiceImpl implements VendorService {

	private final VendorRepository vendorRepository;
	private final VendorMapper mapper;
	
	public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper mapper) {
		this.vendorRepository = vendorRepository;
		this.mapper = mapper;
	}

	@Override
	public List<VendorDTO> getAllVendors() {
		return vendorRepository.findAll()
				.stream()
				.map(vendor -> {
					VendorDTO vendorDTO = mapper.vendorToVendorDTO(vendor);
					vendorDTO.setUrl(getVendorUrl(vendor.getId()));
					return vendorDTO;
				})
				.collect(Collectors.toList());
	}
	
    private String getVendorUrl(Long id) {
        return VendorController.BASE_URL + "/" + id;
    }
}