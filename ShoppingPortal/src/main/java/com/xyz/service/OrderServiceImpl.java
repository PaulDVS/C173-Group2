package com.xyz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.xyz.entities.BasketItem;
import com.xyz.entities.BasketItemFull;
import com.xyz.entities.Item;
import com.xyz.entities.OrderRecord;
import com.xyz.entities.OrderRecords;
import com.xyz.entities.ResultImp;
import com.xyz.entities.StockItem;
import com.xyz.entities.User;

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
	public String addItem(String currentUserEmail, int ItemId, int quantity) {
		String results;
		OrderRecord currentOrders = new OrderRecord();
		
		OrderRecords currentOrdersList =  restTemplate.getForObject("http://localhost:8082/Orders/Unconfirmed/"+currentUserEmail, OrderRecords.class);
		if(!currentOrdersList.getOrderRecordList().isEmpty()) {
			currentOrders = currentOrdersList.getOrderRecordList().get(0);
		} else {
			currentOrders = restTemplate.getForObject("http://localhost:8082/Orders/Create/"+currentUserEmail, OrderRecord.class);
		}
		

		HttpHeaders header = new HttpHeaders();
		HttpEntity<OrderRecord> entity = new HttpEntity<OrderRecord>(header);

		ResultImp<OrderRecord> result = restTemplate.exchange("http://localhost:8082/Orders/Items/Add/"+currentOrders.getOrderId()+"/"+ItemId+"/"+quantity, HttpMethod.POST, entity, ResultImp.class).getBody();
		
		if(result.getMessage().equals("The Item(s) is/are added Successfully")) {
			Item item = restTemplate.getForObject("http://localhost:8081/Items/Id/"+ItemId, Item.class);
			StockItem changedItem = new StockItem(item.getId(), (item.getQuantity()-quantity));
			restTemplate.put("http://localhost:8081/Items/Quantity/Set", changedItem);
		}
		
		return result.getMessage();
	}


	@Override
	public String removeItem(String currentUserEmail, int basketItemId) {
		
		OrderRecords currentOrdersList =  restTemplate.getForObject("http://localhost:8082/Orders/Unconfirmed/"+currentUserEmail, OrderRecords.class);
		OrderRecord currentOrders = currentOrdersList.getOrderRecordList().get(0);

		HttpHeaders header = new HttpHeaders();
		HttpEntity<OrderRecord> entity = new HttpEntity<OrderRecord>(header);

		ResultImp<OrderRecord> result = restTemplate.exchange("http://localhost:8082/Orders/Items/Remove/"+currentOrders.getOrderId()+"/"+basketItemId, HttpMethod.POST, entity, ResultImp.class).getBody();
		
		
		return result.getMessage();
	}

	@Override
	public void confirmOrder(String currentUserEmail) {
		OrderRecords currentOrdersList = restTemplate.getForObject("http://localhost:8082/Orders/Unconfirmed/"+currentUserEmail, OrderRecords.class);
		if(!currentOrdersList.getOrderRecordList().isEmpty()) {
			OrderRecord currentOrders = currentOrdersList.getOrderRecordList().get(0);
			int orderID = currentOrders.getOrderId();
			
			restTemplate.put("http://localhost:8082/Orders/Confirm/"+orderID, null);
		}
	}

}
