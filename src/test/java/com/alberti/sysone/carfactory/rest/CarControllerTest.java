package com.alberti.sysone.carfactory.rest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.alberti.sysone.carfactory.exception.CarNotFoundException;
import com.alberti.sysone.carfactory.service.CarFactoryService;

@WebMvcTest(CarController.class)
class CarControllerTest {

	@Autowired
	private MockMvc carControllerMock;
	
	@MockBean
	private CarFactoryService carFactoryService;
	
	@MockBean
	private CarModelAssembler carModelAssembler;
	
	@Test
	void testAll_EmptyList() throws Exception {
		when(carFactoryService.all()).thenReturn(new ArrayList<>());
		
		this.carControllerMock.perform(get("/cars"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("{\"_links\":{\"self\":{\"href\":\"http://localhost/cars\"}}}")));
	}
	

	@Test
	void testGet_CarNotFound() throws Exception {
		long unknownId = 2222;
		when(carFactoryService.findById(unknownId)).thenThrow(new CarNotFoundException(unknownId));
		
		carControllerMock.perform(get("/cars/"+ unknownId))
		.andDo(print())
		.andExpect(status().isNotFound())
		.andExpect(content().string(containsString("Car not found "+ unknownId)));
	}
}
