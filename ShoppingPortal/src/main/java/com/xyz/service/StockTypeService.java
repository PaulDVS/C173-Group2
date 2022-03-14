package com.xyz.service;

import com.xyz.entities.ItemTypes;

public interface StockTypeService {
	public ItemTypes getAllItemTypes();
	public int getTaxRate(String itemType);
	public void setTaxRate(String itemTypeId, float taxRate);
}
