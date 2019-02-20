package io.joca.rest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.joca.rest.api.v1.mapper.VendorMapper;
import io.joca.rest.api.v1.model.Vendor;
import io.joca.rest.api.v1.model.VendorDTO;
import io.joca.rest.api.v1.model.VendorListDTO;
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
	public VendorListDTO getAllVendors() {
		return new VendorListDTO(vendorRepository.findAll()
				.stream()
				.map(vendor -> {
					VendorDTO vendorDTO = mapper.vendorToVendorDTO(vendor);
					vendorDTO.setUrl(getVendorUrl(vendor.getId()));
					return vendorDTO;
				})
				.collect(Collectors.toList()));
	}
	
	@Override
	public VendorDTO getVendorrById(Long id) {
		return vendorRepository.findById(id)
				.map(mapper::vendorToVendorDTO)
				.map(vendorDTO -> {
					vendorDTO.setUrl(getVendorUrl(id));
					return vendorDTO;
				})
				.orElseThrow(ResourceNotFoundException::new);
	}

	@Override
	public VendorDTO createNewVendor(VendorDTO vendorDTO) {
		return saveAndReturnDTO(mapper.vendorDTOTVendor(vendorDTO));
	}

	@Override
	public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
		Vendor vendor = mapper.vendorDTOTVendor(vendorDTO);
		vendor.setId(id);
		return saveAndReturnDTO(vendor);
	}

	@Override
	public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id).map(vendor -> {

            if(vendorDTO.getName() != null){
                vendor.setName(vendorDTO.getName());
            }

            VendorDTO returnDto = mapper.vendorToVendorDTO(vendorRepository.save(vendor));
            returnDto.setUrl(getVendorUrl(id));
            return returnDto;
        }).orElseThrow(ResourceNotFoundException::new);
	}

	@Override
	public void deleteVendorById(Long id) {
		vendorRepository.deleteById(id);
	}
	
	private VendorDTO saveAndReturnDTO(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);
        VendorDTO returnDto = mapper.vendorToVendorDTO(savedVendor);
        returnDto.setUrl(getVendorUrl(savedVendor.getId()));

        return returnDto;
	}
	
    private String getVendorUrl(Long id) {
        return VendorController.BASE_URL + "/" + id;
    }
}