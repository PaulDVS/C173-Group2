package com.xyz.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class EditCheckedOutException extends Exception {
	
	@Getter
	@Setter
	private String message;
}
