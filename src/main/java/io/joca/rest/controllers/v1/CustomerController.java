package io.joca.rest.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.joca.rest.api.v1.model.CustomerDTO;
import io.joca.rest.api.v1.model.CustomerListDTO;
import io.joca.rest.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 18, 2019
 *
 */
@Api("Customer controller")
@Controller
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

	public static final String BASE_URL = "/api/v1/customers";
	
	private CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	@ApiOperation(value = "Returns a list of all customers.", notes = "Just testing some notes")
	@GetMapping
	public ResponseEntity<CustomerListDTO> getAllCustomers() {
		return new ResponseEntity<CustomerListDTO>(
				new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get Customer by Id")
	@GetMapping("/{id}")
	public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
		return new ResponseEntity<CustomerDTO>(
				customerService.getCustomerById(id), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Create a new Customer.")
    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer (
    		@RequestBody CustomerDTO customerDTO) {
    	
        return new ResponseEntity<CustomerDTO>(
        		customerService.createNewCustomer(customerDTO), HttpStatus.CREATED);
    }
    
	@ApiOperation(value = "Update an existing Customer")
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer (@PathVariable Long id,
    		@RequestBody CustomerDTO customerDTO) {
    	
        return new ResponseEntity<CustomerDTO>(
        		customerService.saveCustomerByDTO(id, customerDTO), HttpStatus.OK);
    }
    
	@ApiOperation(value = "Update a Customer Property")
    @PatchMapping({"/{id}"})
    public ResponseEntity<CustomerDTO> patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(customerService.patchCustomer(id, customerDTO),
                HttpStatus.OK);
    }
    
	@ApiOperation(value = "Delete a Customer")
    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){

        customerService.deleteCustomerById(id);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}