package com.xyz.service;

import java.util.List;
import java.util.Optional;

import com.xyz.entity.BasketItem;
import com.xyz.entity.BasketItems;
import com.xyz.entity.OrderRecord;

public interface OrderService {

    public List<OrderRecord> getAllOrderRecords();

    public OrderRecord findOrderByOrderId(int orderId);

    public String findCustomerEmailByOrderId(int orderId);

    public List<OrderRecord> findOrdersByCustomerEmail(String cEmail);

    public List<OrderRecord> getAllCheckedOutOrder();

    public List<OrderRecord> getAllUncheckedOutOrder();

    public List<OrderRecord> getAllCheckedOutOrderByEmail(String cEmail);

    public List<OrderRecord> getAllUncheckedOutOrderByEmail(String cEmail);

    public OrderRecord createOrderRecord(String cEmail);

    public OrderRecord addBasketItemsToOrder(int orderId, List<Integer> itemIds, List<Integer> quantities) throws EditCheckedOutException;

    public OrderRecord removeBasketItemsFromOrderByQuantity(int orderId, List<Integer> basketItemIds, List<Integer> quantities) throws EditCheckedOutException;

    public OrderRecord removeBasketItemsFromOrder(int orderId, List<Integer> basketItemIds) throws EditCheckedOutException;
    
    public OrderRecord addNewItemToOrder(Optional<OrderRecord> result, BasketItem basketItem);

    public OrderRecord addItemToOrder(Optional<OrderRecord> result, int itemPosition, BasketItem basketItem);

    public OrderRecord subtractItemFromOrder(Optional<OrderRecord> result, int itemPosition, int quantity);

    public OrderRecord confirmOrder(int orderId);
}
