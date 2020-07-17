package com.alberti.sysone.carfactory.service;

import com.alberti.sysone.carfactory.jpa.model.Car;

public interface PriceService {
	
	Double getPrice(Car car);
}
