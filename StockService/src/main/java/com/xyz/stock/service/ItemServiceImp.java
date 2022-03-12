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
	
	private final float NO_PRICE = Float.NaN; 
	private final int NO_QUANTITY = Integer.MIN_VALUE; 
	
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
		Optional<Item> optResult = itemDao.findById(itemId);
		if(!optResult.isEmpty()) {
			return optResult.get();
		}
		
		return new Item(itemId,"", null, 0.0f, 0) ;
	}
	
	public int getStockQuantityById(int itemId) {
		
		Optional<Item> optResult = itemDao.findById(itemId);
		if(!optResult.isEmpty()) {
			Item result = optResult.get();
			return result.getQuantity();
		}
		return NO_QUANTITY;
		
	}
	public float getPriceById(int itemId) {
		
		Optional<Item> optResult = itemDao.findById(itemId);
		if(!optResult.isEmpty()) {
			Item result = optResult.get();
			return result.getPrice();
		}
		
		return NO_PRICE;
	}

	
	public void setStockQuantityById(StockItem stockItem) {
		Optional<Item> optResult = itemDao.findById(stockItem.getItemId());
		if(!optResult.isEmpty()) {
			Item result = optResult.get();
			result.setQuantity(stockItem.getQuantity());
			itemDao.save(result);
		}
	}
	
}
