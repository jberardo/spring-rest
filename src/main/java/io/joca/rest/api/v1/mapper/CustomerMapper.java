package io.joca.rest.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import io.joca.rest.api.v1.model.Customer;
import io.joca.rest.api.v1.model.CustomerDTO;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 18, 2019
 *
 */
@Mapper
public interface CustomerMapper {

	CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
	CustomerDTO customerToCustomerDTO(Customer customer);
	Customer customerDtoToCustomer(CustomerDTO customerDTO);
}