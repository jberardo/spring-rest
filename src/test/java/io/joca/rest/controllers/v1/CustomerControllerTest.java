package io.joca.rest.controllers.v1;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import io.joca.rest.api.v1.model.CustomerDTO;
import io.joca.rest.service.CustomerService;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 18, 2019
 *
 */
public class CustomerControllerTest {

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
        customer1.setId(1l);
        customer1.setFirstname(FNAME);
        customer1.setLastname(FNAME);

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setId(2l);
        customer2.setFirstname(FNAME2);
        customer2.setLastname(FNAME2);

        List<CustomerDTO> customers = Arrays.asList(customer1, customer2);

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

//    @Test
//    public void testGetByNameCategories() throws Exception {
//        CategoryDTO category1 = new CategoryDTO();
//        category1.setId(1l);
//        category1.setName(NAME);
//
//        when(categoryService.getCategoryByName(anyString())).thenReturn(category1);
//
//        mockMvc.perform(get("/api/v1/categories/Jim")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", equalTo(NAME)));
//    }
}