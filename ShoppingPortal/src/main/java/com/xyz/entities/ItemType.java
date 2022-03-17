package com.xyz.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ItemType {
	
	@Getter
	@Setter
	private String type;
	@Getter
	@Setter
	private float taxRate;
	
}
