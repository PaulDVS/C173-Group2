package com.xyz.service;

import java.util.List;

import com.xyz.entity.BasketItem;
import com.xyz.entity.BasketItems;
import com.xyz.persistence.BasketItemDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketServiceImpl implements BasketService{
    
    @Autowired
	private BasketItemDao basketItemDao;

    @Override
    public BasketItem addToBasket(BasketItem basketItem) {
        BasketItem result = basketItemDao.findById(basketItem.getBasketItemId()).get();
        if(result != null){
            basketItemDao.addQuantityById(basketItem.getOrderRecord().getOrderId(), basketItem.getItemId(), result.getQuantity() + basketItem.getQuantity());
        }
        return basketItemDao.save(basketItem);
    }

    @Override
    public BasketItem removeFromBasket(BasketItem basketItem) {
        BasketItem result = basketItemDao.findById(basketItem.getBasketItemId()).get();
        if(result != null){
            if(result.getQuantity() >= basketItem.getQuantity()) basketItemDao.addQuantityById(basketItem.getOrderRecord().getOrderId(), basketItem.getItemId(), result.getQuantity() - basketItem.getQuantity());
        }
        return basketItem;
    }

	@Override
	public BasketItems getAllBasketItemByItemId(int itemId) {
        BasketItems basketItems = new BasketItems(basketItemDao.getAllBasketItemByItemId(itemId));
		return basketItems;
	}

	@Override
	public BasketItems getCheckedOutBasketItemByCustomerId(String customerEmail) {
        BasketItems basketItems = new BasketItems(basketItemDao.getCheckedOutBasketItemByCustomerId(customerEmail));
		return basketItems;
	}

	@Override
	public BasketItems getUnCheckedOutBasketItemByCustomerId(String customerEmail) {
        BasketItems basketItems = new BasketItems(basketItemDao.getUnCheckedOutBasketItemByCustomerId(customerEmail));
		return basketItems;
	}

	@Override
	public BasketItem discardBasketItem(BasketItem basketItem) {
        BasketItem result = basketItemDao.findById(basketItem.getBasketItemId()).get();
        if(result != null){
            return basketItemDao.discardBasketItemByOrderId(basketItem.getOrderRecord().getOrderId());
        }
		return basketItem;
	}
}
