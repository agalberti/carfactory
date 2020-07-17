package com.alberti.sysone.carfactory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alberti.sysone.carfactory.exception.CarNotFoundException;
import com.alberti.sysone.carfactory.jpa.model.Car;
import com.alberti.sysone.carfactory.jpa.repository.CarRepository;

@Service
public class CarFactoryServiceImpl implements CarFactoryService {

	@Autowired
	private CarRepository carRepository;
	@Autowired
	private PriceService priceService;
	
	@Override
	public List<Car> all() {
		return carRepository.findAll();
	}

	@Override
	public Car findById(Long id) {
		return carRepository.findById(id).
				orElseThrow(() -> new CarNotFoundException(id));
	}

	@Override
	public Car deleteById(Long id) {
		Car car = findById(id);
		carRepository.delete(car);
		return car;
	}

	@Override
	public Car save(Car car) {
		// calculate price
		car.setPrice(priceService.getPrice(car));
		// save it
		return carRepository.saveAndFlush(car);
	}

	@Override
	public Car update(Long id, Car updatedCar) {
		// calculate price
		updatedCar.setPrice(priceService.getPrice(updatedCar));
		return carRepository.findById(id)
				.map(current -> {
					current.setName(updatedCar.getName());
					current.setVariant(updatedCar.getVariant());
					current.setEquipments(updatedCar.getEquipments());
					current.setPrice(updatedCar.getPrice());
					return carRepository.save(current);
				})
				.orElseGet(() -> {
					updatedCar.setId(id);
					return carRepository.save(updatedCar);
				});
	}

}
