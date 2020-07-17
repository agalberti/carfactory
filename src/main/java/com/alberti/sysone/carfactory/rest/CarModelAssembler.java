package com.alberti.sysone.carfactory.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.alberti.sysone.carfactory.jpa.model.Car;

@Component
public class CarModelAssembler implements RepresentationModelAssembler<Car, EntityModel<Car>> {

	@Override
	public EntityModel<Car> toModel(Car car) {

		return EntityModel.of(car,
				linkTo(methodOn(CarController.class).get(car.getId())).withSelfRel(),
				linkTo(methodOn(CarController.class).all()).withRel("cars"));
	}

}
