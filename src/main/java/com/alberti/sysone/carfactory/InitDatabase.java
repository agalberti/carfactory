package com.alberti.sysone.carfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.alberti.sysone.carfactory.jpa.model.Car;
import com.alberti.sysone.carfactory.jpa.model.Equipment;
import com.alberti.sysone.carfactory.jpa.model.Variant;
import com.alberti.sysone.carfactory.jpa.repository.CarRepository;
import com.alberti.sysone.carfactory.jpa.repository.EquipmentRepository;
import com.alberti.sysone.carfactory.jpa.repository.VariantRepository;

@Component
public class InitDatabase {

	private static final Logger log = LoggerFactory.getLogger(CarFactoryApplication.class);
	
	@Autowired
	private VariantRepository varRep;
	@Autowired
	private EquipmentRepository equipRep;
	@Autowired
	private CarRepository carRep;
	
	@Bean
	public CommandLineRunner initDB() {
		return (args) -> {
			// create variantes
			Variant sedan = new Variant("Sedan", Double.valueOf(230000));
			varRep.save(sedan);
			varRep.save(new Variant("Familiar", Double.valueOf(245000)));
			varRep.save(new Variant("Coupe", Double.valueOf(270000)));
			
			// create Equipments
			Equipment tc = new Equipment("Techo Corredizo", Double.valueOf(12000));
			Equipment abs = new Equipment("ABS", Double.valueOf(14000));
			equipRep.save(tc);
			equipRep.save(new Equipment("Aire Acondicionado", Double.valueOf(20000)));
			equipRep.save(abs);
			equipRep.save(new Equipment("Airbag", Double.valueOf(7000)));
			equipRep.save(new Equipment("Llantas Aleacion", Double.valueOf(12000)));
			
			// create some autos
			Car car1 = new Car("auto 1", sedan);
			car1.addEquipment(tc);
			car1.addEquipment(abs);
			car1.setPrice(Double.valueOf(256000));
			carRep.save(car1);
//			autoRep.save(new Car("auto 2"));
//			autoRep.save(new Car("auto 3"));
//			autoRep.save(new Car("auto 4"));
			
			// get all variantes
			log.info("Variantes found with findAll():");
			log.info("-------------------------------");
			for (Variant v : varRep.findAll()) {
				log.info(v.toString());
			}
			log.info("");
			
			// get all Equipments
			log.info("Equipments found with findAll():");
			log.info("-------------------------------");
			for (Equipment e : equipRep.findAll()) {
				log.info(e.toString());
			}
			log.info("");
			
			// get all cars
			log.info("Cars found with findAll():");
			log.info("-------------------------------");
			for (Car c : carRep.findAll()) {
				log.info(c.toString());
			}
			log.info("");
		};
	}
}
