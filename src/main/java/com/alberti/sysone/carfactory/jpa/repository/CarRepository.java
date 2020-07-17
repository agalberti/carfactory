package com.alberti.sysone.carfactory.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alberti.sysone.carfactory.jpa.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
