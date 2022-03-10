package com.xyz.service;

import com.xyz.entity.Order;
import com.xyz.entity.BasketItem;

public interface BasketService {
    public BasketItem addToBasket(BasketItem basketItem);
    public BasketItem removeFromBasket(BasketItem basketItem);
    // public Order checkOut(Order basket);
}
