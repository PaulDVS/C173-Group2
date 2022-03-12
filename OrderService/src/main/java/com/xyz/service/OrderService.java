package com.xyz.service;

import java.util.List;

import com.xyz.entity.BasketItem;
import com.xyz.entity.BasketItems;
import com.xyz.entity.OrderRecord;

public interface OrderService {
    public String findCustomerEmailByOrderId(int id);

    public OrderRecord findOrderByCustomerEmail(String cEmail);

    public OrderRecord addBasketItemsToOrder(int OrderId, BasketItems basketItems);

    public OrderRecord removeBasketItemsToOrder(int OrderId, BasketItems basketItems);

    // public OrderRecord setBasketItemQuantityToOrder(int OrderId, BasketItem basketItem, int quantity);

    public OrderRecord confirmOrder(int OrderId);
}
