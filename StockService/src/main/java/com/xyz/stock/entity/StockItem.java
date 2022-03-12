package com.xyz.stock.entity;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class StockItem {
	@Getter
	@Setter
	@Id
	private int itemId;
	@Getter
	@Setter
	private int quantity;
}
