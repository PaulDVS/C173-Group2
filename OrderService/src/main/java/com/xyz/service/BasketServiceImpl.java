package com.xyz.service;

import com.xyz.entity.BasketItem;
import com.xyz.persistence.BasketItemDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketServiceImpl implements BasketService{
    
    @Autowired
	private BasketItemDao basketItemDao;

    @Override
    public BasketItem addToBasket(BasketItem basketItem) {
        BasketItem result = basketItemDao.findById(basketItem.getItemId()).get();
        if(result != null){
            basketItemDao.addQuantityById(basketItem.getOrderId(), basketItem.getItemId(), basketItem.getQuantity());
        }
        return basketItemDao.save(basketItem);
    }

    @Override
    public BasketItem removeFromBasket(BasketItem basketItem) {
        BasketItem result = basketItemDao.findById(basketItem.getItemId()).get();
        if(result != null){
            basketItemDao.subQuantityById(basketItem.getOrderId(), basketItem.getItemId(), basketItem.getQuantity());
        }
        return basketItem;
    }
}
