package com.xyz.service;

import com.xyz.entity.OrderRecord;

public interface OrderService {
    public OrderRecord checkOut(int orderId);
}
