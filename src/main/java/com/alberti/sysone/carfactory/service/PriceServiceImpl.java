package com.alberti.sysone.carfactory.service;

import org.springframework.stereotype.Service;

import com.alberti.sysone.carfactory.jpa.model.Car;

@Service
public class PriceServiceImpl implements PriceService {

	@Override
	public Double getPrice(Car car) {
		// what happens if automovil is null? optional?
		Double priceVariant = car.getVariant().getPrice();
		Double priceEquipments = car.getEquipments().stream()
				.mapToDouble(e -> e.getPrice())
				.sum();
		return Double.sum(priceVariant, priceEquipments);
	}

}
