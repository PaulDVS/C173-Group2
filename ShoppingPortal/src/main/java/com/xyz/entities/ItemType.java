package com.xyz.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class ItemType {
	
	@Getter
	@Setter
//	@Id
	private String type;
	@Getter
	@Setter
	private float taxRate;
	
}
