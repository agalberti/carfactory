package com.alberti.sysone.carfactory.rest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;

import com.alberti.sysone.carfactory.jpa.model.Car;

class CarModelAssemblerTest {

	private CarModelAssembler assembler = new CarModelAssembler();
	
	@Test
	void testToModel() {
		Car car = new Car();
		
		EntityModel<Car> carModel = assembler.toModel(car);
		
		assertThat(carModel).isNotNull();
		assertThat(carModel.getContent()).isEqualTo(car);
		assertThat(carModel.getLink(IanaLinkRelations.SELF)).isNotEmpty();
		assertThat(carModel.getLink("cars")).isNotEmpty();
	}

}
