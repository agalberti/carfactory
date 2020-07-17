package com.alberti.sysone.carfactory.jpa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Variant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	private Double price;

	@OneToMany (mappedBy = "variant", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Car> cars = new HashSet<>();
	
	public Variant() {
	}

	public Variant(String name, Double price) {
		this.name = name;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}
	
	@Override
	public String toString() {
		return "Variant = (id: "+ id+ " Name: "+ name+ " Price: "+ price+ ")";
	}
}
