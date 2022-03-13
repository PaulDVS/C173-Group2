package com.xyz.service;

import java.util.List;
import java.util.Optional;

import com.xyz.entity.BasketItem;
import com.xyz.entity.BasketItems;
import com.xyz.entity.OrderRecord;

public interface OrderService {

    public List<OrderRecord> getAllOrderRecords();

    public String findCustomerEmailByOrderId(int orderId);

    public OrderRecord findOrderByOrderId(int orderId);

    public List<OrderRecord> findOrdersByCustomerEmail(String cEmail);

    public OrderRecord createOrderRecord(OrderRecord orderRecord);


    public OrderRecord addBasketItemsToOrder(int OrderId, BasketItems basketItems) throws AddBasketItemException;

    public OrderRecord removeBasketItemsFromOrder(int OrderId, BasketItems basketItems);

    // public OrderRecord addBasketItemsToOrder(int OrderId, BasketItems basketItems);

    // public OrderRecord setBasketItemQuantityToOrder(int OrderId, BasketItem basketItem, int quantity);

    public OrderRecord confirmOrder(int OrderId);
}
