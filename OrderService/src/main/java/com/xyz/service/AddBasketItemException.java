package com.xyz.service;

import org.aspectj.apache.bcel.ExceptionConstants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor

public class AddBasketItemException extends Exception {
	
	@Getter
	@Setter
	private String message;
	

}
