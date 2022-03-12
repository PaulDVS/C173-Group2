package com.xyz.service;

import java.util.List;

import com.xyz.entity.OrderRecord;

public interface OrderService {
    public List<String> findCustomerEmailByOrderId(int id);
    public OrderRecord findOrderByCustomerEmail(String cEmail);
    public OrderRecord checkOutOrderById(int OrderId);
}
