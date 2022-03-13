package com.xyz.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Getter
	@Setter
	private String customerEmail;
	@Getter
	@Setter
	private String customerPhone;
	@Getter
	@Setter
	private String customerAddress;
	@Getter
	@Setter
	private String customerName;
	@Getter
	@Setter
	private String customerPassword;
}
