package io.joca.rest.service;

import java.util.List;

import io.joca.rest.api.v1.model.CustomerDTO;

public interface CustomerService {
	List<CustomerDTO> getAllCustomers();
	CustomerDTO getCustomerById(Long id);
	CustomerDTO createNewCustomer(CustomerDTO customerDTO);
	CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO);
	CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);
	void deleteCustomerById(Long id);
}