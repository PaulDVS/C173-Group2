package com.xyz.entities;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderRecord{

	@Getter
	@Setter
	private int orderId;

	@Getter
	@Setter
	private String customerEmail;

    @Getter
	@Setter
	private boolean checkedOut;

	@Getter
	@Setter
  	private List<BasketItem> items;
}
	
