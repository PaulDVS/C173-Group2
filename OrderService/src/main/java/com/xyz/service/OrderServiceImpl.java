package com.xyz.service;

import java.util.List;

import com.xyz.entity.OrderRecord;
import com.xyz.persistence.OrderRecordDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
	private OrderRecordDao orderRecordDao;

    @Override
    public List<String> findCustomerEmailByOrderId(int id) {
        return orderRecordDao.findCustomerEmailByOrderId(id);
    }

    @Override
    public OrderRecord findOrderByCustomerEmail(String cEmail) {
        return orderRecordDao.findOrderByCustomerEmail(cEmail);
    }

    @Override
    public OrderRecord checkOutOrderById(int orderId) {

        return orderRecordDao.checkOutOrderById(orderId);
    }
}
