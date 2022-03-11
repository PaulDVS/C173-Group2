package com.xyz.service;

import com.xyz.entity.Order;
import com.xyz.persistence.OrderDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
	private OrderDao orderDao;

    @Override
    public Order checkOut(int orderId) {
        return orderDao.checkOutOrderById(orderId);
    }
}
