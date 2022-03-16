package com.xyz.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
public class Login {
	
	@Getter
	@Setter
	private String userName;
	@Getter
	@Setter
	private String password;
	@Getter
	@Setter
	private boolean rememberMe; 

}
