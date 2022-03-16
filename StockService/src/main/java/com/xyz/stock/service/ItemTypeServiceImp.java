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

	private final float NO_RATE = Float.NaN; 

	
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

		ItemType foundItem = itemTypeDao.getById(itemTypeId);

		if (foundItem!=null) {
			foundItem.setTaxRate(taxRate);
			itemTypeDao.save(foundItem);
			return foundItem;
		}
		return new ItemType("Not Found",Float.NaN);
	}
@Override
	public float getTaxtRate(String itemTypeId) {

		ItemType foundItem = itemTypeDao.getById(itemTypeId);

		if (foundItem!=null) {
			return foundItem.getTaxRate();
		}
		return NO_RATE;
	}
}
