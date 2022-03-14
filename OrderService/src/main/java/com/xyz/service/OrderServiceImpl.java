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


    @Override
    public OrderRecord addBasketItemsToOrder(int OrderId, BasketItems basketItems) throws EditCheckedOutException {
        Optional<OrderRecord> result = orderRecordDao.findById(OrderId);
        if(!result.isEmpty()){
        	if(!result.get().isCheckedOut()) {
        		basketItems.getItems().forEach(basketItem -> {
                    result.get().getItems().add(basketItem);
                });
                return orderRecordDao.save(result.get());
        	}
            else throw new EditCheckedOutException("The order has already been confirmed.");
        }
        return null;
    }

    @Override
    public OrderRecord removeBasketItemsFromOrder(int OrderId, List<Integer> basketItemIds) throws EditCheckedOutException {
        Optional<OrderRecord> result = orderRecordDao.findById(OrderId);
        if(!result.isEmpty()){
            if(!result.get().isCheckedOut()){
                basketItemIds.forEach(basketItemId -> {
                    BasketItem basketItemToRemove = basketItemDao.getById(basketItemId);
                    result.get().getItems().remove(basketItemToRemove);
                    basketItemDao.delete(basketItemToRemove);
                });
                return orderRecordDao.save(result.get());
            }
            else throw new EditCheckedOutException("The order has already been confirmed.");
        }
        return null;
    }

    @Override
    public List<OrderRecord> getAllCheckedOutOrder() {
        return orderRecordDao.getAllCheckedOutOrder();
    }

    @Override
    public List<OrderRecord> getAllUncheckedOutOrder() {
        return orderRecordDao.getAllUncheckedOutOrder();
    }

    @Override
    public List<OrderRecord> getAllCheckedOutOrderByEmail(String cEmail) {
        return orderRecordDao.getAllCheckedOutOrderByEmail(cEmail);
    }

    @Override
    public List<OrderRecord> getAllUncheckedOutOrderByEmail(String cEmail) {
        return orderRecordDao.getAllUncheckedOutOrderByEmail(cEmail);
    }
 
}
