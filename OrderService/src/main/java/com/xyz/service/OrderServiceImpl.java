package com.xyz.service;

import java.util.List;
import java.util.Optional;

import com.xyz.entity.BasketItem;
import com.xyz.entity.BasketItems;
import com.xyz.entity.OrderRecord;
import com.xyz.persistence.BasketItemDao;
import com.xyz.persistence.OrderRecordDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
	private OrderRecordDao orderRecordDao;

    @Autowired
	private BasketItemDao basketItemDao;

    @Override
    public List<OrderRecord> getAllOrderRecords() {
        return orderRecordDao.findAll();
    }

    @Override
    public String findCustomerEmailByOrderId(int orderId) {
        return orderRecordDao.findCustomerEmailByOrderId(orderId);
    }

    @Override
    public OrderRecord findOrderByOrderId(int orderId) {
        return orderRecordDao.findById(orderId).get();
    }

    @Override
    public List<OrderRecord> findOrdersByCustomerEmail(String cEmail) {
        return orderRecordDao.findOrderByCustomerEmail(cEmail);
    }

    @Override
    public OrderRecord createOrderRecord(OrderRecord orderRecord) {
        return orderRecordDao.save(orderRecord);
    }

    // @Override
    // public OrderRecord addBasketItemsToOrder(int OrderId, BasketItems basketItems) {
    //     Optional<OrderRecord> result = orderRecordDao.findById(OrderId);
    //     if(!result.isEmpty()){
    //         basketItems.getListItems().forEach(basketItem -> {
    //             result.get().getListItems().add(basketItem);
    //         });
    //         return orderRecordDao.save(result.get());
    //     }
    //     return null;
    // }

    // @Override
    // public OrderRecord removeBasketItemsToOrder(int OrderId, BasketItems basketItems) {
    //     Optional<OrderRecord> result = orderRecordDao.findById(OrderId);
    //     if(!result.isEmpty()){
    //         basketItems.getListItems().forEach(basketItem -> {
    //             BasketItem basketItemToRemove = basketItemDao.getById(basketItem.getBasketItemId());
    //         	result.get().getListItems().remove(basketItemToRemove);
    //         });
    //         return orderRecordDao.save(result.get());
    //     }
    //     return null;
    // }

    @Override
    public OrderRecord confirmOrder(int OrderId) {
        Optional<OrderRecord> result = orderRecordDao.findById(OrderId);
        if(!result.isEmpty()){
            OrderRecord orderRecord = result.get();
            orderRecord.setCheckedOut(true);
            return orderRecordDao.save(orderRecord);
        }
        return null;
    }
    
    // @Override
    // public OrderRecord setBasketItemQuantityToOrder(int OrderId, BasketItem basketItem, int quantity) {
    //     Optional<OrderRecord> result = orderRecordDao.findById(OrderId);
    //     result.get().getBasketItems().
    //     Optional<BasketItem> basketItem = orderRecord.find
    //     return null;
    // }

   
}
