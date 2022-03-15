package com.xyz.persistence;

import java.util.List;

import com.xyz.entity.BasketItem;
import com.xyz.entity.OrderRecord;

import org.springframework.data.annotation.Persistent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BasketItemDao extends JpaRepository<BasketItem, Integer>{
    // get all items by ItemId
    @Query("FROM BasketItem WHERE itemId = :itId")
    public List<BasketItem> getAllBasketItemByItemId(@Param("itId") int itemId);

    // // get all items by ItemId and OrderId JOIN TABLES
    // @Query("FROM BasketItem bt INNER JOIN OrderRecord ord WHERE bt.itemId = :itId AND ord.orderId = :oId")
    // public List<BasketItem> getAllBasketItemByItemIdJoinOrderRecord(@Param("itId") int itemId, @Param("oId") int orderId);
}
