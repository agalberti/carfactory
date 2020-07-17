package com.alberti.sysone.carfactory.exception;

public class CarNotFoundException extends RuntimeException {

	public CarNotFoundException(long id) {
		super("Car not found "+ id);
	}
}
