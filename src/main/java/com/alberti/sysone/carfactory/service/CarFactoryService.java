package com.alberti.sysone.carfactory.service;

import java.util.List;

import com.alberti.sysone.carfactory.jpa.model.Car;

public interface CarFactoryService {
	
	List<Car> all();
	
	Car findById(Long id);
	
	Car deleteById(Long id);
	
	Car save(Car car);
	
	Car update(Long id, Car updatedCar);
}
