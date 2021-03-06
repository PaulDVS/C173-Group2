package com.xyz.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Getter
	@Setter
	@Id
	private String customerEmail;
	@Getter
	@Setter
	private String customerPhone;
	@Getter
	@Setter
	private String customerAddress;
	@Getter
	@Setter
	@Column(unique=true) // Makes sure that customer name is unique
	private String customerName;
	@Getter
	@Setter
	private String customerPassword;
}
