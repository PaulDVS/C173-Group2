package com.xyz.service;

import com.xyz.entity.OrderRecord;
import com.xyz.persistence.OrderRecordDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
	private OrderRecordDao orderRecordDao;

    @Override
    public OrderRecord checkOut(int orderId) {
        return orderRecordDao.checkOutOrderById(orderId);
    }
}
