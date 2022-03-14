package com.xyz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.xyz.entities.ItemTypes;

@Service
public class StockTypeServicImp implements StockTypeService{
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ItemTypes getAllItemTypes() {
		return restTemplate.getForObject("http://localhost:8081/ItemTypes/All", ItemTypes.class);
	}
	@Override
	public int getTaxRate(String itemTypeId) {
		return restTemplate.getForObject("http://localhost:8081/ItemTypes/GetTaxRates/ItemType/"+itemTypeId, Integer.class);
	}
	@Override
	public void setTaxRate(String itemTypeId, float taxRate) {
		 restTemplate.put("http://localhost:8081/ItemTypes/GetTaxRates/ItemType/"+itemTypeId + "/"+taxRate,null);
	}
	
}
