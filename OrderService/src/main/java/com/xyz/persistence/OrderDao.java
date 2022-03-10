package com.xyz.persistence;

import java.util.List;

import com.xyz.entity.BasketItem;
import com.xyz.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderDao extends JpaRepository<Order, Integer>{
    @Query("SELECT * FROM Order WHERE customerEmail =: cEmail")
    public Order findOrderByCustomerEmail(@Param("cEmail") String cEmail);

    // @Transactional
	// @Modifying
	// @Query("UPDATE FROM Product WHERE name=:productName")
    // public Order findOrderByCustomerEmail(@Param("cEmail") String cEmail);
}
