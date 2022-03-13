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

    public OrderRecord createOrderRecord(OrderRecord orderRecord);


    public OrderRecord addBasketItemsToOrder(int OrderId, BasketItems basketItems) throws EditCheckedOutException;

    public OrderRecord removeBasketItemsFromOrder(int OrderId, BasketItems basketItems) throws EditCheckedOutException;

    public OrderRecord confirmOrder(int OrderId);
}
