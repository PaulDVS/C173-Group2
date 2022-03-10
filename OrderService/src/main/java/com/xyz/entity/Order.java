package com.xyz.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	@Getter
	@Setter
	@Id
	private int OrderId;

	@Getter
	@Setter
	private String customerEmail;

    @Getter
	@Setter
	private boolean checkedOut;
}
