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
    // @Query("SELECT bt FROM BasketItem bt INNER JOIN bt.orderRecord ord WHERE ord.customerEmail =: cEmail")

    // get all items by ItemId
    @Query("FROM BasketItem WHERE itemId =: itId")
    public List<BasketItem> getAllBasketItemByItemId(@Param("itId") int itemId);

    // get all items from current and past Orders by OrderId
    @Query("FROM BasketItem WHERE orderId =: oId")
    public List<BasketItem> getAllBasketItemByOrderId(@Param("oId") int orderId);

    // // get all items from current and past Orders by CustomerEmail
    // @Query("FROM BasketItem WHERE customerEmail =: cEmail")
    // public List<BasketItem> getAllBasketItemByCustomerEmail(@Param("cEmail") String customerEmail);

    // get all items from past Order (checkedOut items) by customerEmail
    @Query("FROM BasketItem WHERE customerEmail =: cEmail AND checkedOut = 1")
    public List<BasketItem> getCheckedOutBasketItemByCustomerId(@Param("cEmail") String customerEmail);

    // get all items from current Order (uncheckedOut items) by customerEmail
    @Query("FROM BasketItem WHERE customerEmail =: cEmail AND checkedOut = 0")
    public List<BasketItem> getUnCheckedOutBasketItemByCustomerId(@Param("cEmail") String customerEmail);

    // update quantity by adding, looked up by orderId and itemId
    @Transactional
	@Modifying
	@Query("UPDATE BasketItem SET quantity=: ItQuantity WHERE itemId =: itId AND OrderId =: oId")
    public BasketItem addQuantityById(@Param("oId") int orderId, @Param("itId") int itemId, @Param("ItQuantity") int quantity);

    // update quantity by subtracting, looked up by orderId and itemId
    @Transactional
	@Modifying
	@Query("UPDATE BasketItem SET quantity=: ItQuantity WHERE itemId =: itId AND OrderId =: oId")
    public BasketItem subQuantityById(@Param("oId") int orderId, @Param("itId") int itemId, @Param("ItQuantity") int quantity);

    // discard basket, only discards unCheckOut items, delete all items by OrderId
    @Transactional
	@Modifying
	@Query("DELETE FROM BasketItem bt WHERE orderId =: oId AND checkOut = 0")
    public BasketItem discardBasketItemByOrderId(@Param("oId") int orderId);
}
