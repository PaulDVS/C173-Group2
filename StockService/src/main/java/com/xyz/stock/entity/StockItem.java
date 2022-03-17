package com.xyz.stock.entity;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
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
