package com.xyz.stock.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Items {
	
	@Getter
	@Setter
	private List<Item>listitem;
}
