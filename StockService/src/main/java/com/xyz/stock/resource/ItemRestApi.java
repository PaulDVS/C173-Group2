package com.xyz.stock.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.stock.entity.Item;
import com.xyz.stock.entity.ItemType;
import com.xyz.stock.entity.Items;
import com.xyz.stock.service.ItemService;

@RestController
public class ItemRestApi {
	
	@Autowired
	private ItemService itemService;
	
	@PostMapping(value="Items/Add",produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Item addItem(@RequestBody Item item) {
		
		
		return itemService.addItem(item);
	}
	
	@GetMapping(value="Items/All",produces=MediaType.APPLICATION_JSON_VALUE)
	public Items getAllItems() {
		
		return itemService.getAllItems();
	}
	@GetMapping(value="Items/Id/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Item getItemById(@PathVariable int id) {
		return itemService.getItemById(id);
	}
	
	@PostMapping(value="Items/Type",produces=MediaType.APPLICATION_JSON_VALUE)
	public Items getItemsByType(@RequestBody ItemType itemType) {
		return itemService.getItemsByType(itemType);
	}
	@GetMapping(value="Items/Price/{id}")
	public float getPriceById(@PathVariable int id) {
		return itemService.getPriceById(id);
	}
	@GetMapping(value="Items/Quantity/{id}")
	public int getStockQuantityById(@PathVariable int id) {
		return itemService.getStockQuantityById(id);
	}

}
