package com.xyz.resource;

import com.xyz.entity.OrderRecord;
import com.xyz.service.AddBasketItemException;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasketApi {
    @Autowired
	private OrderService orderService;

	@GetMapping(value="/Orders",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<OrderRecord> viewItems() {
		return orderService.getAllOrderRecords();
	}

	@PostMapping(value="Orders/Create",produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public OrderRecord createOrder(@RequestBody OrderRecord orderRecord) {
		return orderService.createOrderRecord(orderRecord);
	}


    @PostMapping(value="Orders/Items/Add/{orderId}",produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResultImp<OrderRecord> addItems(@PathVariable int orderId, @RequestBody BasketItems basketItems) {
		
    	ResultImp<OrderRecord> result=new ResultImp<OrderRecord>("The Item added Successfully",null); 
    	try {
			var res = orderService.addBasketItemsToOrder(orderId, basketItems);
			// result = new ResultImp<OrderRecord>("The Item added Successfully",res); 
			result.setObject(res);
			return result;
		} catch (AddBasketItemException e) {
		
			 result .setMessage(e.getMessage()); 
		
		}
		
		return result;
	}

    @DeleteMapping(value="Orders/Items/Remove/{orderId}",produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public OrderRecord removeItems(@PathVariable int orderId, @RequestBody BasketItems basketItems) {
		return orderService.removeBasketItemsFromOrder(orderId, basketItems);
	}

    // @PostMapping(value="Orders/Add/{orderId}",produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	// public OrderRecord addItems(@PathVariable int orderId, @RequestBody BasketItems basketItems) {
	// 	return orderService.addBasketItemsToOrder(orderId, basketItems);
	// }

    // @DeleteMapping(value="Orders/Remove/{orderId}",produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	// public OrderRecord removeItems(@PathVariable int orderId, @RequestBody BasketItems basketItems) {
	// 	return orderService.removeBasketItemsToOrder(orderId, basketItems);
	// }


	@PutMapping(value="Orders/Confirm/{orderId}",produces=MediaType.APPLICATION_JSON_VALUE)
	public OrderRecord confirmOrder(@PathVariable int orderId) {
		return orderService.confirmOrder(orderId);
	}
}
