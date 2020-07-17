package com.alberti.sysone.carfactory.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alberti.sysone.carfactory.jpa.model.Car;
import com.alberti.sysone.carfactory.service.CarFactoryService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class CarController {

	@Autowired
	private CarModelAssembler carModelAssembler;
	@Autowired
	private CarFactoryService carFactoryService;

	@GetMapping("/")
	public RepresentationModel<?> root() {

		RepresentationModel<?> rootResource = new RepresentationModel<>();

		rootResource.add(
				linkTo(methodOn(CarController.class).root()).withSelfRel(), //
				linkTo(methodOn(CarController.class).all()).withRel("cars"));

		return rootResource;
	}

	@GetMapping("/cars")
	CollectionModel<EntityModel<Car>> all() {
		List<EntityModel<Car>> cars = carFactoryService.all()
				.stream()
				.map(carModelAssembler::toModel)
				.collect(Collectors.toList());

		return CollectionModel.of(cars, linkTo(methodOn(CarController.class).all()).withSelfRel());
	}

	@PostMapping("/cars")
	EntityModel<Car> newCar(@RequestBody Car newCar) {
		return carModelAssembler.toModel(carFactoryService.save(newCar));
	}

	@PutMapping("/cars/{id}")
	ResponseEntity<EntityModel<Car>> updateCar(@RequestBody Car updatedCar, @PathVariable Long id) {
		EntityModel<Car> car = carModelAssembler.toModel(carFactoryService.update(id, updatedCar));
		
		return ResponseEntity.created(car.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(car);
	}

	@GetMapping("/cars/{id}")
	ResponseEntity<EntityModel<Car>> get(@PathVariable Long id) {
		EntityModel<Car> car = carModelAssembler.toModel(carFactoryService.findById(id));
		return ResponseEntity.ok(car);
	}

	@DeleteMapping("/cars/{id}")
	ResponseEntity<?> delete(@PathVariable Long id) {
		carFactoryService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
