package com.xyz.service;

import com.xyz.entity.Basket;
import com.xyz.entity.BasketItem;

public interface BasketService {
    public BasketItem addToBasket(int itemId, int quantity);
    public BasketItem removeFromBasket(int itemId, int quantity);
    public Basket checkOut(Basket basket);
}
