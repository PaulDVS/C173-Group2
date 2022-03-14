package com.xyz.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//This entity is just for loading data into the cart page
@AllArgsConstructor
@NoArgsConstructor
public class BasketItemFull {
	
	@Getter
	@Setter
	private int basketItemId;
	@Getter
	@Setter
	private String basketItemName;
	@Getter
	@Setter
	private int basketItemQuantity;
	@Getter
	@Setter
	private float basketItemPrice;
	@Getter
	@Setter
	private float basketItemTax;
	@Getter
	@Setter
	private float basketItemFinalPrice;
	
}
