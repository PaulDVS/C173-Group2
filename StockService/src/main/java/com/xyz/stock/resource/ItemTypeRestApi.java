package com.xyz.stock.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.stock.entity.ItemType;
import com.xyz.stock.entity.ItemTypes;
import com.xyz.stock.service.ItemTypeService;

@RestController
public class ItemTypeRestApi {
	
	@Autowired
	private ItemTypeService itemTypeService;
	
	// handles requests to create a new item type in the database
	@PostMapping(value="/ItemTypes/Create",consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ItemType addItemType (@RequestBody ItemType itemType) {
	return 	itemTypeService.addItemType(itemType);
	}

	// handles requests to list all item types in the database
	@GetMapping(value="/ItemTypes/All", produces = MediaType.APPLICATION_JSON_VALUE)
	public ItemTypes getAllTypes() {
		
		return itemTypeService.getAllTypes();
	}
	
	// handles requests to get the tax rate for an item type from the database
	@GetMapping(value="/ItemTypes/GetTaxRates/ItemType/{itemTypeId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public float getTaxRate(@PathVariable String itemTypeId) {
		return itemTypeService.getTaxtRate(itemTypeId);
	}

	// handles requests to set the tax rate for an item type in the database
	@PostMapping(value="/ItemTypes/SetTaxRates/ItemType/{itemTypeId}/{rate}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ItemType setTaxRate(@PathVariable String itemTypeId,@PathVariable float rate) {
		return itemTypeService.setTaxtRate(itemTypeId,rate);
	}
	
}
