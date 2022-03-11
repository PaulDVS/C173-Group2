package com.xyz.stock.service;

import com.xyz.stock.entity.Item;
import com.xyz.stock.entity.ItemType;
import com.xyz.stock.entity.Items;

public interface ItemService {
	public Item addItem(Item item);
	public Items getAllItems();
	public Items getItemsByType(ItemType itemType);
	public Item getItemById(int itemId);
	public int getStockQuantityById(int itemId);
	public float getPriceById(int itemId);
}
