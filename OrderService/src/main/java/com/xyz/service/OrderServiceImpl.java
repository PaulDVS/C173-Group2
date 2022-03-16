package com.xyz.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public OrderRecord createOrderRecord(String cEmail) {
        OrderRecord orderRecordToAdd = new OrderRecord();
        orderRecordToAdd.setCustomerEmail(cEmail);
        orderRecordToAdd.setCheckedOut(false);
        return orderRecordDao.save(orderRecordToAdd);
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
    public OrderRecord addBasketItemsToOrder(int orderId, List<Integer> itemIds, List<Integer> quantities) throws EditCheckedOutException {
        Optional<OrderRecord> result = orderRecordDao.findById(orderId);
        if(!result.isEmpty() && itemIds.size() == quantities.size()){
            if(!result.get().isCheckedOut()) {
                List<BasketItem> tempBasket = result.get().getItems();
                Collections.sort(tempBasket);
                for(int i = 0; i < itemIds.size(); i++){
                    BasketItem tempBt = new BasketItem(1, itemIds.get(i), quantities.get(i));
                     // if basketItem with specific ItemId already exist
                    int position = Collections.binarySearch(tempBasket, tempBt);
                    if(position >= 0){
                        this.addItemToOrder(result, position, tempBt);
                    }
                    else this.addNewItemToOrder(result, tempBt);
                }
                return orderRecordDao.save(result.get());
            }
            else throw new EditCheckedOutException("The order has already been confirmed.");
        }
        return null;
    }

    @Override
    public OrderRecord removeBasketItemsFromOrderByQuantity(int orderId, List<Integer> basketItemIds, List<Integer> quantities) throws EditCheckedOutException {
        Optional<OrderRecord> result = orderRecordDao.findById(orderId);
        if(!result.isEmpty() && basketItemIds.size() == quantities.size()){
            if(!result.get().isCheckedOut()){
                List<BasketItem> tempBasket = result.get().getItems();
                Collections.sort(tempBasket);
                for(int i = 0; i < basketItemIds.size(); i++){
                    if(!basketItemDao.findById(basketItemIds.get(i)).isEmpty()){
                        int position = Collections.binarySearch(tempBasket, basketItemDao.findById(basketItemIds.get(i)).get());
                        System.out.println(position);
                        this.subtractItemFromOrder(result, position, quantities.get(i));
                    }
                }
                return orderRecordDao.save(result.get());
            }
            else throw new EditCheckedOutException("The order has already been confirmed.");
        }
        return null;
    }

    @Override
    public OrderRecord removeBasketItemsFromOrder(int orderId, List<Integer> basketItemIds) throws EditCheckedOutException {
        Optional<OrderRecord> result = orderRecordDao.findById(orderId);
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

    @Override
    public OrderRecord addNewItemToOrder(Optional<OrderRecord> result, BasketItem basketItem){
        result.get().getItems().add(basketItem);
        return orderRecordDao.save(result.get());
    }

    @Override
    public OrderRecord addItemToOrder(Optional<OrderRecord> result, int itemPosition, BasketItem basketItem){
        BasketItem basketItemToEdit = result.get().getItems().get(itemPosition);
        basketItemToEdit.setQuantity(basketItemToEdit.getQuantity() + basketItem.getQuantity());
        basketItemDao.save(basketItemToEdit);
        return orderRecordDao.save(result.get());	
    }

    @Override
    public OrderRecord subtractItemFromOrder(Optional<OrderRecord> result, int itemPosition, int quantity) {
        BasketItem basketItemToRemove = result.get().getItems().get(itemPosition);
        if(basketItemToRemove.getQuantity() - quantity > 0){
            basketItemToRemove.setQuantity(basketItemToRemove.getQuantity() - quantity);
            basketItemDao.save(basketItemToRemove);
        }
        else {
            result.get().getItems().remove(basketItemToRemove);
            basketItemDao.delete(basketItemToRemove);
        }
        return orderRecordDao.save(result.get());
    }
}