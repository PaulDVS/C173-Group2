package com.xyz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.xyz.entities.Item;
import com.xyz.entities.ItemType;
import com.xyz.entities.ItemTypes;
import com.xyz.entities.Items;
import com.xyz.entities.StockItem;
import com.xyz.entities.User;

@Service
public class StockServiceImpl implements StockService {
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Item addItem(Item item) {
		return restTemplate.postForObject("http://localhost:8081/Items/Add", item, Item.class);
	}

	@Override
	public Items getAllItems() {
		return restTemplate.getForObject("http://localhost:8081/Items/All", Items.class);
	}

	@Override
	public Items getItemsByType(ItemType itemType) {
		return restTemplate.postForObject("http://localhost:8081/Items/Type", itemType, Items.class);
	}

	@Override
	public Item getItemById(int itemId) {
		return restTemplate.getForObject("http://localhost:8081/Items/Id/" + itemId, Item.class);

	}

	@Override
	public int getStockQuantityById(int itemId) {
		
		int result = restTemplate.getForObject("http://localhost:8081/Items/Quantity/" + itemId, Integer.class);
	
	return result;
	}

	@Override
	public float getPriceById(int itemId) {
		return restTemplate.getForObject("http://localhost:8081/Items/Price/" + itemId, Float.class);
	}

	@Override
	public void setStockQuantityById(StockItem stockItem) {
		restTemplate.put("http://localhost:8081/Items/Quantity/Set/", stockItem);
	}
	@Override
	public ItemTypes getAllItemTypes() {
		return restTemplate.getForObject("http://localhost:8081/ItemTypes/All", ItemTypes.class);
	}
	@Override
	public int getTaxRate(String itemType) {
		return restTemplate.getForObject("http://localhost:8081/ItemTypes/GetTaxRates/ItemType/"+itemType, Integer.class);
	}
	@Override
	public void setTaxRate(String itemTypeId, float taxRate) {
		 restTemplate.postForObject("http://localhost:8081/ItemTypes/GetTaxRates/ItemType/"+itemTypeId + "/"+taxRate,null,null);
	}

}
