package com.alberti.sysone.carfactory.rest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.alberti.sysone.carfactory.exception.CarNotFoundException;
import com.alberti.sysone.carfactory.jpa.model.Car;
import com.alberti.sysone.carfactory.service.CarFactoryService;

@WebMvcTest(CarController.class)
class CarControllerTest {

	@Autowired
	private MockMvc carControllerMock;
	
	@MockBean
	private CarFactoryService carFactoryService;
	
	@MockBean
	private CarModelAssembler carModelAssembler;
	
	private String exampleCarJson = "{\"name\": \"auto 4\",\"variant\": {\"id\": 1,\"name\": \"Coupe\",\"price\": 270000},\"equipments\": [{\"id\": 1,\"name\": \"Techo Corredizo\",\"price\": 12000},{\"id\": 3,\"name\": \"ABS\",\"price\": 14000}]}";

	
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
	
	@Test
	void testNewCarSuccess() throws Exception {
		Car car = new Car();
		
		when(carFactoryService.save(Mockito.any(Car.class))).thenReturn(car);
		when(carModelAssembler.toModel(Mockito.any(Car.class))).thenReturn(EntityModel.of(car));
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/cars")
				.accept(MediaType.APPLICATION_JSON)
				.content(exampleCarJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		carControllerMock.perform(requestBuilder)
		.andDo(print())
		.andExpect(status().isOk());
		
		
		verify(carFactoryService).save(Mockito.any(Car.class));
		verify(carModelAssembler).toModel(car);
	}
}
