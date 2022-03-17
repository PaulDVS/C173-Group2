package com.xyz.stock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.stock.entity.Item;
import com.xyz.stock.entity.ItemType;
import com.xyz.stock.entity.Items;
import com.xyz.stock.entity.StockItem;
import com.xyz.stock.persistence.ItemDao;

@Service
public class ItemServiceImp implements ItemService {
	
	@Autowired
	private ItemDao itemDao;
	
	public Item addItem(Item item) {
		
		return  itemDao.save(item);
		
	}
	
	public Items getAllItems() {
		List<Item> listItem= itemDao.findAll();
		return new Items(listItem);
		
	}
	public Items getItemsByType(ItemType itemType) {
		List<Item> items= itemDao.findItemsByItemType(itemType);
		return new Items (items);

	}
	public Item getItemById(int itemId) {
		var optResult = itemDao.findById(itemId);
		if(!optResult.isEmpty()) {
			return optResult.get();
		}
		
		return new Item(itemId,"No Name", null, 0.0f, 0) ;
	}
	
	public int getStockQuantityById(int itemId) {
		
		return itemDao.getStockQuantityById(itemId);
		
	}
	public float getPriceById(int itemId) {
		
			return itemDao.getPriceById(itemId);	
	}

	
	public void setStockQuantityById(StockItem stockItem) {
		
		Item foundItem = itemDao .getById(stockItem.getItemId());
		if(foundItem != null) {
			foundItem.setQuantity(stockItem.getQuantity());
			  itemDao.save(foundItem);
		}
		
	}
	
}
