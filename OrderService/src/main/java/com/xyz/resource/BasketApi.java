package com.xyz.resource;

import com.xyz.entity.OrderRecord;
import com.xyz.service.OrderService;
import com.xyz.entity.BasketItem;
import com.xyz.entity.BasketItems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasketApi {
    @Autowired
	private OrderService orderService;

    @PostMapping(value="Basket/addItems",produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public OrderRecord addItem(@RequestBody int orderId, @RequestBody BasketItems basketItems) {
		return orderService.addBasketItemsToOrder(orderId, basketItems);
	}

    @PostMapping(value="Basket/removeItems",produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public OrderRecord removeItem(@RequestBody int orderId, @RequestBody BasketItems basketItems) {
		return orderService.removeBasketItemsToOrder(orderId, basketItems);
	}
}
