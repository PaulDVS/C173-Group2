package com.xyz.stock.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.stock.entity.Item;
import com.xyz.stock.entity.ItemType;
import com.xyz.stock.entity.Items;
import com.xyz.stock.entity.StockItem;
import com.xyz.stock.service.ItemService;

@RestController
public class ItemRestApi {
	
	@Autowired
	private ItemService itemService;
	
	// handles requests to add an item to the database
	@PostMapping(value="/Items/Add",produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Item addItem(@RequestBody Item item) {
		return itemService.addItem(item);
	}
	
	// handles requests to get all items from the database
	@GetMapping(value="/Items/All",produces=MediaType.APPLICATION_JSON_VALUE)
	public Items getAllItems() {
		
		return itemService.getAllItems();
	}

	// handles requests to get an item from the database by ID
	@GetMapping(value="/Items/Id/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Item getItemById(@PathVariable int id) {
		return itemService.getItemById(id);
	}

	// handles requests to get items of a certain type from the database
	@PostMapping(value="/Items/Type",produces=MediaType.APPLICATION_JSON_VALUE)
	public Items getItemsByType(@RequestBody ItemType itemType) {
		return itemService.getItemsByType(itemType);
	}
	
	// handles requests to get the price of an item by its ID from the database
	@GetMapping(value="/Items/Price/{id}")
	public float getPriceById(@PathVariable int id) {
		return itemService.getPriceById(id);
	}
	
	// handles requests to get the available quantity of an item by its ID from the database
	@GetMapping(value="/Items/Quantity/{id}")
	public int getStockQuantityById(@PathVariable int id) {
		return itemService.getStockQuantityById(id);
	}
	
	// handles requests to set the available quantity of an item in the database
	@PutMapping(value="/Items/Quantity/Set")
	public void setStockQuantityById(@RequestBody StockItem stockItem) {
		itemService.setStockQuantityById(stockItem);
	}

}
