package io.joca.rest.controllers.v1;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import io.joca.rest.api.v1.model.Vendor;
import io.joca.rest.api.v1.model.VendorDTO;
import io.joca.rest.api.v1.model.VendorListDTO;
import io.joca.rest.service.ResourceNotFoundException;
import io.joca.rest.service.VendorService;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 19, 2019
 *
 */
public class VendorControllerTest extends AbstractRestControllerTest {

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

        VendorListDTO vendors = new VendorListDTO(Arrays.asList(vendor1, vendor2));

        when(vendorService.getAllVendors()).thenReturn(vendors);

        mock.perform(get(VendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }
    
    @Test
    public void testGetVendorById() throws Exception {
        VendorDTO vendor = new VendorDTO();
        vendor.setName("Vendor1");

        when(vendorService.getVendorrById(anyLong())).thenReturn(vendor);

        mock.perform(get(VendorController.BASE_URL + "/123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Vendor1")));
    }
    
    @Test
    public void testUpdateVendor() throws Exception {
        //given
        Vendor vendor = new Vendor();
        vendor.setName("Vendor1");

        VendorDTO returnDTO = new VendorDTO();
        returnDTO.setName(vendor.getName());
        returnDTO.setUrl(VendorController.BASE_URL + "/1");

        when(vendorService.saveVendorByDTO(anyLong(), any(VendorDTO.class))).thenReturn(returnDTO);

        //when/then
        mock.perform(put(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Vendor1")))
                .andExpect(jsonPath("$.url", equalTo(VendorController.BASE_URL + "/1")));
    }
    
    @Test
    public void testPatchVendor() throws Exception {

        //given
        VendorDTO vendor = new VendorDTO();
        vendor.setName("Vendor1");

        VendorDTO returnDTO = new VendorDTO();
        returnDTO.setName("Vendor2");
        returnDTO.setUrl(VendorController.BASE_URL + "/1");

        when(vendorService.patchVendor(anyLong(), any(VendorDTO.class))).thenReturn(returnDTO);

        mock.perform(patch(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Vendor2")))
                .andExpect(jsonPath("$.url", equalTo(VendorController.BASE_URL + "/1")));
    }
    
    @Test
    public void testDeleteCustomer() throws Exception {

        mock.perform(delete(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(vendorService).deleteVendorById(anyLong());
    }

    @Test
    public void testNotFoundException() throws Exception {

        when(vendorService.getVendorrById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mock.perform(get(VendorController.BASE_URL + "/222")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}