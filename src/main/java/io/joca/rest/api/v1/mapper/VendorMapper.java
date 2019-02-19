package io.joca.rest.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import io.joca.rest.api.v1.model.Vendor;
import io.joca.rest.api.v1.model.VendorDTO;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 19, 2019
 *
 */
@Mapper
public interface VendorMapper {
	VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);
	VendorDTO vendorToVendorDTO(Vendor vendor);
}