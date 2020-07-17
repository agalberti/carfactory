package com.alberti.sysone.carfactory.jpa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Car {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;

	@ManyToOne
	private Variant variant;
	
	@ManyToMany
	private Set<Equipment> equipments = new HashSet<>();
	
	private Double price;
	
	public Car() {
	}
	
	public Car(String name, Variant variant) {
		this.name = name;
		this.variant = variant;
	}

	public Variant getVariant() {
		return variant;
	}
	
	public void setVariant(Variant variant) {
		this.variant = variant;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<Equipment> getEquipments() {
		return equipments;
	}

	public void setEquipments(Set<Equipment> equipments) {
		this.equipments = equipments;
	}

	public void addEquipment(Equipment e) {
		this.equipments.add(e);
		e.getCars().add(this);
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
