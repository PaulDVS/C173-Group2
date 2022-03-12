package com.xyz.stock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.stock.entity.ItemType;
import com.xyz.stock.entity.ItemTypes;
import com.xyz.stock.persistence.ItemTypeDao;

@Service
public class ItemTypeServiceImp implements ItemTypeService {

	@Autowired
	private ItemTypeDao itemTypeDao;

	// gets all item types from the database
	@Override
	public ItemTypes getAllTypes() {
		List<ItemType> types = itemTypeDao.findAll();
		return new ItemTypes(types);
	}

	// adds a new item type to the database
	@Override
	public ItemType addItemType(ItemType itemType) {

		return itemTypeDao.save(itemType);
	}
	
	// sets the tax rate for an item type in the database
	@Override
	public ItemType setTaxtRate(String itemTypeId, float taxRate) {

		var foundItem = itemTypeDao.findById(itemTypeId).get();

		if (foundItem!=null) {
			foundItem.setTaxRate(taxRate);
			itemTypeDao.save(foundItem);
			return foundItem;
		}
		return null;
	}
	
	// gets the tax rate for an item type from the database
	@Override
	public float getTaxtRate(String itemTypeId) {

		var foundItem = itemTypeDao.findById(itemTypeId);

		if (!foundItem.isEmpty()) {
			ItemType itemType = foundItem.get();
			return itemType.getTaxRate();
		}
		return -1.0f;
	}
}
