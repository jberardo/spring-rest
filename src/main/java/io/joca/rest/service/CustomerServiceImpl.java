package io.joca.rest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.joca.rest.api.v1.mapper.CustomerMapper;
import io.joca.rest.api.v1.model.CustomerDTO;
import io.joca.rest.repositories.CustomerRepository;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 18, 2019
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;
	
	public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
		super();
		this.customerRepository = customerRepository;
		this.customerMapper = customerMapper;
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {
		return customerRepository.findAll()
				.stream()
				.map(customerMapper::customerToCustomerDTO)
				.collect(Collectors.toList());
	}
}