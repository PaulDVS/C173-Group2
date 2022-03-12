package com.xyz.service;

import com.xyz.entity.OrderRecord;

import java.util.List;

import com.xyz.entity.BasketItem;
import com.xyz.entity.BasketItems;

public interface BasketService {
    public BasketItems getAllBasketItemByItemId(int itemId);
    public BasketItems getCheckedOutBasketItemByCustomerId(String customerEmail);
    public BasketItems getUnCheckedOutBasketItemByCustomerId(String customerEmail);
    public BasketItem addToBasket(BasketItem basketItem);
    public BasketItem removeFromBasket(BasketItem basketItem);
    public BasketItem discardBasketItem(BasketItem basketItem);
}
