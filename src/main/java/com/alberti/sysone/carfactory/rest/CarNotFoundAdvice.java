package com.alberti.sysone.carfactory.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alberti.sysone.carfactory.exception.CarNotFoundException;

@ControllerAdvice
public class CarNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(CarNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String carNotFoundHandler(CarNotFoundException cnfe) {
		return cnfe.getMessage();
	}
}
