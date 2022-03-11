package com.xyz.stock.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Item {
	
	@Getter
	@Setter
	@Id
	private int id;
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="type")
	private ItemType type;
	@Getter
	@Setter
	private float price;
	@Getter
	@Setter
	private int quantity;

}
