package com.xyz.entities;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Getter
	@Setter
	@NotEmpty(message = "Email address is mandatory")
	private String customerEmail;
	@Getter
	@Setter
	@NotEmpty(message = "Phone Number is mandatory")
	private String customerPhone;
	@Getter
	@Setter
	@NotEmpty(message = "Address is mandatory")
	private String customerAddress;
	@Getter
	@Setter
	@NotEmpty(message = "Full Name is mandatory")
	private String customerName;
	@Getter
	@Setter
	@NotEmpty(message = "Password is mandatory")
	private String customerPassword;
}
