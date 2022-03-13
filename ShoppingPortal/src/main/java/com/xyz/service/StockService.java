package com.xyz.service;

import com.xyz.entities.Item;
import com.xyz.entities.ItemType;
import com.xyz.entities.ItemTypes;
import com.xyz.entities.Items;
import com.xyz.entities.StockItem;

public interface StockService {
	public Item addItem(Item item);
	public Items getAllItems();
	public Items getItemsByType(ItemType itemType);
	public Item getItemById(int itemId);
	public int getStockQuantityById(int itemId);
	public float getPriceById(int itemId);
	public void setStockQuantityById(StockItem stockItem);
	public ItemTypes getAllItemTypes();
	public int getTaxRate(String itemType);
	public void setTaxRate(String itemTypeId, float taxRate);
}
