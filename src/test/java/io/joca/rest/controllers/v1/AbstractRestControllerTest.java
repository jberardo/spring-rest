package io.joca.rest.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 18, 2019
 *
 */
public class AbstractRestControllerTest {

	public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}