package com.xyz.service;

import com.xyz.entity.OrderRecord;
import com.xyz.entity.BasketItem;

public interface BasketService {
    public BasketItem addToBasket(BasketItem basketItem);
    public BasketItem removeFromBasket(BasketItem basketItem);
}
