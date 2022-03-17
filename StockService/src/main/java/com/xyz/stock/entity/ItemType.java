package com.xyz.stock.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ItemType {
	
	@Getter
	@Setter
	@Id
	private String type;
	@Getter
	@Setter
	private float taxRate;
	
}
