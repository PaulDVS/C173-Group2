package com.xyz.service;

import java.util.List;

import com.xyz.entities.BasketItemFull;

public interface OrderService {
	public List<BasketItemFull> showCart(String currentUserEmail);
	public String addItem(String currentUserEmail, int ItemId, int quantity);
	public String removeItem(String currentUserEmail, int basketItemId);
	public void confirmOrder(String currentUserEmail);
}
