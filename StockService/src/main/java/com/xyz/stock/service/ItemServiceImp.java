package com.xyz.stock.service;

import java.util.List;
import java.util.Optional;

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
	
	// default values of attributes
	private final float NO_PRICE = Float.NaN; 
	private final int NO_QUANTITY = Integer.MIN_VALUE; 
	
	// adds an item to the database
	public Item addItem(Item item) {
		return  itemDao.save(item);
	}
	
	// gets all items from the database
	public Items getAllItems() {
		List<Item> listItem= itemDao.findAll();
		return new Items(listItem);
	}
	
	// gets all items of a certain type from the database
	public Items getItemsByType(ItemType itemType) {
		List<Item> items= itemDao.findItemsByItemType(itemType);
		return new Items (items);
	}
	
	// gets in item from the database by ID
	public Item getItemById(int itemId) {
		Optional<Item> optResult = itemDao.findById(itemId);
		if(!optResult.isEmpty()) {
			return optResult.get();
		}
		
		return new Item(itemId,"", null, 0.0f, 0) ;
	}
	
	// gets the available quantity of an item from the database by its ID
	public int getStockQuantityById(int itemId) {
		
		Optional<Item> optResult = itemDao.findById(itemId);
		if(!optResult.isEmpty()) {
			Item result = optResult.get();
			return result.getQuantity();
		}
		return NO_QUANTITY;
	}
	
	// gets the price of an item from the database by its ID
	public float getPriceById(int itemId) {
		
		Optional<Item> optResult = itemDao.findById(itemId);
		if(!optResult.isEmpty()) {
			Item result = optResult.get();
			return result.getPrice();
		}
		
		return NO_PRICE;
	}

	// sets the available quantity of an item in the database
	public void setStockQuantityById(StockItem stockItem) {
		Optional<Item> optResult = itemDao.findById(stockItem.getItemId());
		if(!optResult.isEmpty()) {
			Item result = optResult.get();
			result.setQuantity(stockItem.getQuantity());
			itemDao.save(result);
		}
	}
	
}
