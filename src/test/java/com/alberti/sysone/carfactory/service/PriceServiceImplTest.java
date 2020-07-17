package com.alberti.sysone.carfactory.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.alberti.sysone.carfactory.jpa.model.Car;
import com.alberti.sysone.carfactory.jpa.model.Equipment;
import com.alberti.sysone.carfactory.jpa.model.Variant;
import com.alberti.sysone.carfactory.service.PriceServiceImpl;

class PriceServiceImplTest {

	private PriceServiceImpl service = new PriceServiceImpl();
	private Variant sedan = new Variant("sedan", Double.valueOf(230000));
	private Equipment techoCorredizo = new Equipment("techo corredizo", Double.valueOf(12000));

	@Test
	void testGetCostoFinalSedanBase() {
		// sedan with no equipments
		Car car = new Car();
		car.setVariant(sedan);
		
		assertEquals(Double.valueOf(230000), service.getPrice(car));
	}
	
	@Test
	void testGetCostoFinalSedanPlusTC() {
		// sedan with techo corredizo
		Car car = new Car();
		car.setVariant(sedan);
		car.addEquipment(techoCorredizo);
		assertEquals(Double.valueOf(242000), service.getPrice(car));
	}
	
}
