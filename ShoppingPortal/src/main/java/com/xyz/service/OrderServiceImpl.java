package com.xyz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.xyz.entities.BasketItem;
import com.xyz.entities.BasketItemFull;
import com.xyz.entities.Item;
import com.xyz.entities.OrderRecord;
import com.xyz.entities.OrderRecords;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private RestTemplate restTemplate;
	
	
	//Returns a list of BasketItemFull, an object that displays all the required info for the cart page
	
	@Override
	public List<BasketItemFull> showCart(String currentUserEmail) {
		List<BasketItemFull> basketItemsFull = new ArrayList();
		BasketItemFull currentBasketItem;
		
		int id;
		String name;
		int quantity;
		float price;
		float tax;
		float finalPrice;
		Item currentItem;
		
		OrderRecords currentOrdersList =  restTemplate.getForObject("http://localhost:8082/Orders/Unconfirmed/"+currentUserEmail, OrderRecords.class);
		
		if(!currentOrdersList.getOrderRecordList().isEmpty()) {
			//There should only be one OrderRecord, but a list is returned in case something went wrong
			//It is assumed the first record is the correct OrderRecord
			OrderRecord currentOrders = currentOrdersList.getOrderRecordList().get(0);
			//Creates A BasketItemFull object for each normal basketItem and loads it into the list
			for(BasketItem basketItem : currentOrders.getItems()) {
				id = basketItem.getBasketItemId();
				quantity = basketItem.getQuantity();
				
				currentItem = restTemplate.getForObject("http://localhost:8081/Items/Id/"+basketItem.getItemId(), Item.class);
	
				
				name = currentItem.getName();
				price = currentItem.getPrice();
				tax = currentItem.getType().getTaxRate();
				
				finalPrice = (quantity*price) + ((quantity*price)*(tax/100));
				
				currentBasketItem = new BasketItemFull(id, name, quantity, price, tax, finalPrice);
				basketItemsFull.add(currentBasketItem);
			}
		}
				
		return basketItemsFull;
	}

	@Override
	public String addItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String removeItem() {
		// TODO Auto-generated method stub
		return null;
	}

}
