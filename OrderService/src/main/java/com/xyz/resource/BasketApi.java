package com.xyz.resource;

import com.xyz.entity.OrderRecord;
import com.xyz.service.OrderService;

import java.util.List;
import java.util.Optional;

import com.xyz.entity.BasketItem;
import com.xyz.entity.BasketItems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasketApi {
    @Autowired
	private OrderService orderService;

	@GetMapping(value="/Orders",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<OrderRecord> viewItems() {
		return orderService.getAllOrderRecords();
	}

	@PostMapping(value="Orders/create",produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public OrderRecord createOrder(@RequestBody OrderRecord orderRecord) {
		return orderService.createOrderRecord(orderRecord);
	}

    @PostMapping(value="Orders/add",produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public OrderRecord addItems(@RequestBody int orderId, @RequestBody BasketItems basketItems) {
		return orderService.addBasketItemsToOrder(orderId, basketItems);
	}

    @PostMapping(value="Orders/remove",produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public OrderRecord removeItems(@RequestBody int orderId, @RequestBody BasketItems basketItems) {
		return orderService.removeBasketItemsToOrder(orderId, basketItems);
	}
}
