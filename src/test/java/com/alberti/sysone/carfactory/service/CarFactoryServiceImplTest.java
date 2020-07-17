package com.alberti.sysone.carfactory.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.alberti.sysone.carfactory.exception.CarNotFoundException;
import com.alberti.sysone.carfactory.jpa.model.Car;
import com.alberti.sysone.carfactory.jpa.repository.CarRepository;

@SpringBootTest
class CarFactoryServiceImplTest {
	
	@InjectMocks
	private CarFactoryService carFactoryService = new CarFactoryServiceImpl();
	
	@Mock
	private CarRepository carRepository;
	
	@Mock
	private PriceService priceService;
	
	private Car car;
	private long carId = 22222;
	
	@BeforeEach
	void setMockOutput() {
		car = new Car();
		car.setId(carId);
	}
	
	@Test
	void testAll() {
		when(carRepository.findAll()).thenReturn(new ArrayList<>());

		List<Car> cars = carFactoryService.all();
		assertTrue(new ArrayList<>().equals(cars));
	}

	@Test()
	void testFindById_CarNotFound() {
		long unknownId = 222;
		when(carRepository.findById(unknownId)).thenReturn(Optional.empty());
		
		Exception e = assertThrows(CarNotFoundException.class, () -> {
			carFactoryService.findById(unknownId);
		});
		
		String expectedMessage = "Car not found "+ unknownId;
		assertTrue(expectedMessage.equals(e.getMessage()));
	}
	
	@Test()
	void testFindById_Success() {
		when(carRepository.findById(carId)).thenReturn(Optional.of(car));
		
		Car carFound = carFactoryService.findById(carId);
		assertEquals(carId, carFound.getId());
	}

	@Test
	void testDeleteById_CarNotFound() {
		long unknownId = 222;
		when(carRepository.findById(unknownId)).thenReturn(Optional.empty());
		
		Exception e = assertThrows(CarNotFoundException.class, () -> {
			carFactoryService.deleteById(unknownId);
		});
		
		String expectedMessage = "Car not found "+ unknownId;
		assertTrue(expectedMessage.equals(e.getMessage()));
	}
	
	@Test
	void testDeleteById_Success() {
		when(carRepository.findById(carId)).thenReturn(Optional.of(car));
		
		Car carDeleted = carFactoryService.deleteById(carId);
		assertEquals(carId, carDeleted.getId());
	}

	@Test
	void testSave() {
		Car toSave = new Car();
		
		when(carRepository.saveAndFlush(toSave)).thenReturn(toSave);
		
		Car saved = carFactoryService.save(toSave);
		
		verify(priceService).getPrice(toSave);
		verify(carRepository).saveAndFlush(toSave);
		assertNotNull(saved);
	}

	@Test
	void testUpdate_Success() {
		Car toUpdate = new Car();
		long id = 2323;
		
		when(carRepository.save(toUpdate)).thenReturn(toUpdate);
		
		Car updated = carFactoryService.update(id, toUpdate);
		
		verify(priceService).getPrice(toUpdate);
		verify(carRepository).findById(id);
		verify(carRepository).save(toUpdate);
		assertNotNull(updated);
	}

}

