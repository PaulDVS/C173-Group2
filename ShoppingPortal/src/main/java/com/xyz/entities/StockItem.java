package com.xyz.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class StockItem {
	@Getter
	@Setter
	private int itemId;
	@Getter
	@Setter
	private int quantity;
}
