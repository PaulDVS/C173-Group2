package com.xyz.resource;

import com.xyz.entity.OrderRecord;
import com.xyz.entity.OrderRecords;
import com.xyz.service.EditCheckedOutException;
import com.xyz.service.OrderService;
import com.xyz.service.ResultImp;

import java.util.List;
import java.util.Optional;

import com.xyz.entity.BasketItem;
import com.xyz.entity.BasketItems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasketApi {
    @Autowired
	private OrderService orderService;

	@GetMapping(value="/Orders", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<OrderRecord> viewItems() {
		return orderService.getAllOrderRecords();
	}

	@GetMapping(value="/Orders/Confirmed", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<OrderRecord> viewCheckedOutItems() {
		return orderService.getAllCheckedOutOrder();
	}

	@GetMapping(value="/Orders/Unconfirmed", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<OrderRecord> viewUnCheckedOutItems() {
		return orderService.getAllUncheckedOutOrder();
	}

	@GetMapping(value="/Orders/Confirmed/{cEmail}", produces=MediaType.APPLICATION_JSON_VALUE)
	public OrderRecords viewCheckedOutItemsById(@PathVariable String cEmail) {
		return new OrderRecords(orderService.getAllCheckedOutOrderByEmail(cEmail));
	}

	@GetMapping(value="/Orders/Unconfirmed/{cEmail}", produces=MediaType.APPLICATION_JSON_VALUE)
	public OrderRecords viewUnCheckedOutItemsById(@PathVariable String cEmail) {
		return new OrderRecords(orderService.getAllUncheckedOutOrderByEmail(cEmail));
	}

	@PostMapping(value="Orders/Create",produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public OrderRecord createOrder(@RequestBody OrderRecord orderRecord) {
		return orderService.createOrderRecord(orderRecord);
	}


    @PostMapping(value="Orders/Items/Add/{orderId}",produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResultImp<OrderRecord> addItems(@PathVariable int orderId, @RequestBody BasketItems basketItems) {
		
    	ResultImp<OrderRecord> result = new ResultImp<OrderRecord>("The Item is added Successfully", null); 
    	try {
			var res = orderService.addBasketItemsToOrder(orderId, basketItems);
			result.setObject(res);
			return result;
		} 
		catch (EditCheckedOutException e) {
			result.setMessage(e.getMessage()); 
		}
		return result;
	}

	@DeleteMapping(value="Orders/Items/Remove/{orderId}/{basketItemIds}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResultImp<OrderRecord> removeItems(@PathVariable int orderId, @PathVariable List<Integer> basketItemIds) {
		ResultImp<OrderRecord> result = new ResultImp<OrderRecord>("The Item is removed Successfully", null);
		try {
			var res = orderService.removeBasketItemsFromOrder(orderId, basketItemIds);
			result.setObject(res);
			return result;
		} 
		catch (EditCheckedOutException e) {
			result.setMessage(e.getMessage()); 
		}
		return result;
	}

	@PutMapping(value="Orders/Confirm/{orderId}",produces=MediaType.APPLICATION_JSON_VALUE)
	public OrderRecord confirmOrder(@PathVariable int orderId) {
		return orderService.confirmOrder(orderId);
	}
}
