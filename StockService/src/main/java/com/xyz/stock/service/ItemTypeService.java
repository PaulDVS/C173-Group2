package com.xyz.stock.service;

import com.xyz.stock.entity.ItemType;
import com.xyz.stock.entity.ItemTypes;

public interface ItemTypeService {
	public ItemTypes getAllTypes();
	public ItemType addItemType(ItemType itemType);
	public ItemType setTaxtRate(String itemTypeId, float taxRate);
	public float getTaxtRate(String itemTypeId);
}
