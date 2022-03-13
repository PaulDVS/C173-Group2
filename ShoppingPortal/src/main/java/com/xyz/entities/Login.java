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
	public String userName;
	@Getter
	@Setter
	public String password;
	
	
}
