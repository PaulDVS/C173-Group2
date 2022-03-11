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

	@Override
	public ItemTypes getAllTypes() {
		List<ItemType> types = itemTypeDao.findAll();
		return new ItemTypes(types);
	}

	@Override
	public ItemType addItemType(ItemType itemType) {

		return itemTypeDao.save(itemType);
	}
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
