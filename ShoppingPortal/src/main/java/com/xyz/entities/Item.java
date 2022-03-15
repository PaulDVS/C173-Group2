package com.xyz.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Item {
	
	@Getter
	@Setter
	private int id;
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
//	@ManyToOne
//	@JoinColumn(name="type")
	private ItemType type;
	@Getter
	@Setter
	private float price;
	@Getter
	@Setter
	private int quantity;

}
