package com.xyz.persistence;

import java.util.List;

import com.xyz.entity.BasketItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BasketItemDao extends JpaRepository<BasketItem, Integer>{

    // get all items from OrderId
    @Query("SELECT * FROM BasketItem WHERE orderId =: orId")
    public List<BasketItem> getAllBasketItemByOrderId(@Param("orId") int orderId);

    // get all items by ItemId
    @Query("SELECT * FROM BasketItem WHERE itemId =: itId")
    public List<BasketItem> getAllBasketItemByItemId(@Param("itId") int itemId);

    // get all items from current and past Orders by customerEmail
    @Query("SELECT * FROM BasketItem INNER JOIN Order ON BasketItem.OrderId = Order.OrderId WHERE customerEmail =: cEmail")
    public List<BasketItem> getAllBasketItemByCustomerId(@Param("cEmail") String customerEmail);

    // get all items from past Order (checkOut) by customerEmail
    @Query("SELECT * FROM BasketItem INNER JOIN Order ON BasketItem.OrderId = Order.OrderId WHERE Order.customerEmail =: cEmail AND Order.checkOut = 1")
    public List<BasketItem> getCheckedOutBasketItemByCustomerId(@Param("cEmail") String customerEmail);

    // get all items from current Order (uncheckOut) by customerEmail
    @Query("SELECT * FROM BasketItem INNER JOIN Order ON BasketItem.OrderId = Order.OrderId WHERE Order.customerEmail =: cEmail AND Order.checkOut = 0")
    public List<BasketItem> getUnCheckedOutBasketItemByCustomerId(@Param("cEmail") String customerEmail);

    // update quantity by adding, looked up by orderId and itemId
    @Transactional
	@Modifying
	@Query("UPDATE BasketItem SET quantity=: quantity + ItQuantity WHERE itemId =: itId AND OrderId =: oId")
    public BasketItem addQuantityById(@Param("oId") int orderId, @Param("itId") int itemId, @Param("ItQuantity") int quantity);

    // update quantity by subtracting, looked up by orderId and itemId
    @Transactional
	@Modifying
	@Query("UPDATE BasketItem SET quantity=: quantity - ItQuantity WHERE itemId =: itId AND OrderId =: oId")
    public BasketItem subQuantityById(@Param("oId") int orderId, @Param("itId") int itemId, @Param("ItQuantity") int quantity);

    // discard basket, only discards unCheckOut items, delete all items by OrderId
    @Transactional
	@Modifying
	@Query("DELETE FROM BasketItem INNER JOIN Order ON BasketItem.OrderId = Order.OrderId WHERE OrderId =: oId AND Order.checkOut = 0")
    public BasketItem discardBasketItemByOrderId(@Param("oId") int orderId);
}
