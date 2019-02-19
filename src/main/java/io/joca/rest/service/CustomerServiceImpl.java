package io.joca.rest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.joca.rest.api.v1.mapper.CustomerMapper;
import io.joca.rest.api.v1.model.Customer;
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
				.map(customer -> {
					CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
					customerDTO.setUrl("/api/v1/customers/" + customer.getId());
					return customerDTO;
				})
				.collect(Collectors.toList());
	}

	@Override
	public CustomerDTO getCustomerById(Long id) {
		return customerRepository.findById(id)
			.map(customer -> {
				CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
				customerDTO.setUrl("/api/v1/customers/" + customer.getId());
				return customerDTO;
			})
			.orElseThrow(RuntimeException::new);
	}

	@Override
	public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        
        return saveAndReturnDTO(customerMapper.customerDtoToCustomer(customerDTO));
	}	
	
	private CustomerDTO saveAndReturnDTO(Customer customer) {
		Customer savedCustomer = customerRepository.save(customer);
		CustomerDTO returnDto = customerMapper.customerToCustomerDTO(savedCustomer);
		returnDto.setUrl("/api/v1/customers/" + savedCustomer.getId());
		
		return returnDto;
	}

	@Override
	public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
		Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
		customer.setId(id);
		
		return saveAndReturnDTO(customer);
	}
}