package io.joca.rest.controllers.v1;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.joca.rest.api.v1.model.CustomerDTO;
import io.joca.rest.service.CustomerService;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 18, 2019
 *
 */
public class CustomerControllerTest extends AbstractRestControllerTest {

    public static final String FNAME = "John";
    public static final String LNAME = "Doe";
    
    public static final String FNAME2 = "Jane";
    public static final String LNAME2 = "Doe";

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }
	
    @Test
    public void testListCategories() throws Exception {
        CustomerDTO customer1 = new CustomerDTO();
        //customer1.setId(1l);
        customer1.setFirstname(FNAME);
        customer1.setLastname(FNAME);

        CustomerDTO customer2 = new CustomerDTO();
        //customer2.setId(2l);
        customer2.setFirstname(FNAME2);
        customer2.setLastname(FNAME2);

        List<CustomerDTO> customers = Arrays.asList(customer1, customer2);

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void testGetCustomerById() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstname(FNAME);
        customer.setLastname(LNAME);

        when(customerService.getCustomerById(anyLong())).thenReturn(customer);

        mockMvc.perform(get("/api/v1/customers/123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(FNAME)))
                .andExpect(jsonPath("$.lastname", equalTo(LNAME)));
    }
    
    @Test
    public void createNewCustomer() throws Exception {
        //given
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstname("Fred");
        customer.setLastname("Flintstone");

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstname(customer.getFirstname());
        returnDTO.setLastname(customer.getLastname());
        returnDTO.setUrl("/api/v1/customers/1");

        when(customerService.createNewCustomer(customer)).thenReturn(returnDTO);

        //when/then
        mockMvc.perform(post("/api/v1/customers/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname", equalTo("Fred")))
                .andExpect(jsonPath("$.url", equalTo("/api/v1/customers/1")));
    }
    
    @Test
    public void testUpdateCustomer() throws Exception {
        //given
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstname("Fred");
        customer.setLastname("Flintstone");

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstname(customer.getFirstname());
        returnDTO.setLastname(customer.getLastname());
        returnDTO.setUrl("/api/v1/customers/1");

        when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

        //when/then
        mockMvc.perform(put("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo("Fred")))
                .andExpect(jsonPath("$.lastname", equalTo("Flintstone")))
                .andExpect(jsonPath("$.url", equalTo("/api/v1/customers/1")));
}
}