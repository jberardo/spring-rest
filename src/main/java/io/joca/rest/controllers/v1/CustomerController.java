package io.joca.rest.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.joca.rest.api.v1.model.CustomerDTO;
import io.joca.rest.api.v1.model.CustomerListDTO;
import io.joca.rest.service.CustomerService;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 18, 2019
 *
 */
@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

	private CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	@GetMapping
	public ResponseEntity<CustomerListDTO> getAllCustomers() {
		return new ResponseEntity<CustomerListDTO>(
				new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
		return new ResponseEntity<CustomerDTO>(
				customerService.getCustomerById(id), HttpStatus.OK);
	}
	
    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer (
    		@RequestBody CustomerDTO customerDTO) {
    	
        return new ResponseEntity<CustomerDTO>(
        		customerService.createNewCustomer(customerDTO), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer (@PathVariable Long id,
    		@RequestBody CustomerDTO customerDTO) {
    	
        return new ResponseEntity<CustomerDTO>(
        		customerService.saveCustomerByDTO(id, customerDTO), HttpStatus.OK);
    }
}