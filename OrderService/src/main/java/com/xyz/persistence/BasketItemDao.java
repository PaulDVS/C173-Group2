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
    @Query("SELECT * FROM BasketItem WHERE orderId =: orId")
    public List<BasketItem> findBasketItemByOrderId(@Param("orId") int orderId);

    @Query("SELECT * FROM BasketItem WHERE itemId =: itId")
    public List<BasketItem> findBasketItemByItemId(@Param("itId") int itemId);

    @Transactional
	@Modifying
	@Query("UPDATE BasketItem SET quantity=: quantity + ItQuantity WHERE itemId := itId")
    public BasketItem addQuantityById(@Param("itId") int itemId, @Param("ItQuantity") int quantity);

    @Transactional
	@Modifying
	@Query("UPDATE BasketItem SET quantity=: quantity - ItQuantity WHERE itemId := itId")
    public BasketItem subQuantityById(@Param("itId") int itemId, @Param("ItQuantity") int quantity);

    // @Query("SELECT * FROM BasketItem INNER JOIN Order ON BasketItem.CustomerID = Customers.CustomerID")
    // public List<BasketItem> getCurrentBasket(@Param("orId") int orderId);

}
