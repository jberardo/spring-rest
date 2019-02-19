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

import io.joca.rest.api.v1.model.CategoryDTO;
import io.joca.rest.api.v1.model.VendorDTO;
import io.joca.rest.service.VendorService;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 19, 2019
 *
 */
public class VendorControllerTest {

	private final String NAME = "Vendor1";
	
	@Mock
	private VendorService vendorService;
	
	@InjectMocks
	private VendorController vendorController;
	
	MockMvc mock;
	
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mock = MockMvcBuilders.standaloneSetup(vendorController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }
    
    @Test
    public void testListVendors() throws Exception {
        VendorDTO vendor1 = new VendorDTO();
        vendor1.setName(NAME);

        VendorDTO vendor2 = new VendorDTO();
        vendor2.setName("Vendor2");

        List<VendorDTO> vendors = Arrays.asList(vendor1, vendor2);

        when(vendorService.getAllVendors()).thenReturn(vendors);

        mock.perform(get(VendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }
}