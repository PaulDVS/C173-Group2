package com.xyz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.xyz.entities.Item;
import com.xyz.entities.ItemType;
import com.xyz.entities.Items;
import com.xyz.entities.StockItem;
import com.xyz.entities.User;

@Service
public class StockServiceImpl implements StockService {
	
	@Autowired
	private RestTemplate restTemplate;

	// adds a new item
	@Override
	public Item addItem(Item item) {
		return restTemplate.postForObject("http://localhost:8081/Items/Add", item, Item.class);
	}

	// returns all existing items
	@Override
	public Items getAllItems() {
		return restTemplate.getForObject("http://localhost:8081/Items/All", Items.class);
	}

	// returns all stock items of a certain type
	@Override
	public Items getItemsByType(ItemType itemType) {
		return restTemplate.postForObject("http://localhost:8081/Items/Type", itemType, Items.class);
	}

	// returns an item by its ID
	@Override
	public Item getItemById(int itemId) {
		return restTemplate.getForObject("http://localhost:8081/Items/Id/" + itemId, Item.class);
	}

	// returns the available quantity of an item by its ID
	@Override
	public int getStockQuantityById(int itemId) {
		return restTemplate.getForObject("http://localhost:8081/Items/Quantity/" + itemId, Integer.class);
	}

	// returns the price of an item by its ID
	@Override
	public float getPriceById(int itemId) {
		return restTemplate.getForObject("http://localhost:8081/Items/Price/" + itemId, Float.class);
	}

	// sets the available quantity of an item by its ID
	@Override
	public void setStockQuantityById(StockItem stockItem) {
		restTemplate.put("http://localhost:8081/Items/Quantity/Set/", stockItem);
	}

}
