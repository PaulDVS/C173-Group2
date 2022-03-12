package com.xyz.resource;

import com.xyz.entity.OrderRecord;
import com.xyz.entity.BasketItem;
import com.xyz.service.BasketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasketApi {
    
    // @Autowired
	// private BasketService basketService;

    // @PostMapping(value="Basket/Add",produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	// public BasketItem addItem(@RequestBody BasketItem basketItem) {
	// 	return basketService.addToBasket(basketItem);
	// }

    // @PostMapping(value="Basket/Remove",produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	// public BasketItem removeItem(@RequestBody BasketItem basketItem) {
	// 	return basketService.removeFromBasket(basketItem);
	// }
}
