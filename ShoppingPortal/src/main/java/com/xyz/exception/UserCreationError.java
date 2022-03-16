package com.xyz.exception;

import lombok.Getter;
import lombok.Setter;

public class UserCreationError extends Exception {
	
	@Getter
	@Setter
	private String message;
	
	public UserCreationError(String message) {
		super(message);
		this.message=message;
		
		
	}
}
