package com.xyz.entities;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
public class Login {
	
	@Getter
	@Setter
	@NotBlank(message = "User Name is mandatory")
	private String userName;
	@Getter
	@Setter
	@NotBlank(message = "Password is mandatory")
	private String password;
	@Getter
	@Setter
	private boolean rememberMe; 

}
