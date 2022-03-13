package com.xyz.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor

public class ResultImp<T> {
	@Getter
	@Setter
	private String message;
	@Getter
	@Setter
	private T object;

}
