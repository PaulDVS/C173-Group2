package com.xyz.entities;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
public class Login {
	
	@Getter
	@Setter
	@NotEmpty(message = "User Name is mandatory")
	private String userName;
	@Getter
	@Setter
	@NotEmpty(message = "Password is mandatory")
	private String password;
	@Getter
	@Setter
	private boolean rememberMe; 

}
