package com.xyz.persistence;

import java.util.List;

import javax.transaction.Transactional;

import com.xyz.entity.BasketItem;
import com.xyz.entity.OrderRecord;

import org.springframework.data.annotation.Persistent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRecordDao extends JpaRepository<OrderRecord, Integer>{
    // @Transactional
	// @Modifying

    // get customer Id (email) by Order Id
    @Query("SELECT customerEmail FROM OrderRecord WHERE orderId = :oId")
    public String findCustomerEmailByOrderId(@Param("oId") int orderId);

    // get Order Id by the customer email
    @Query("FROM OrderRecord WHERE customerEmail = :cEmail")
    public List<OrderRecord> findOrderByCustomerEmail(@Param("cEmail") String cEmail);

    // get all checked out orders
    @Query("FROM OrderRecord WHERE checkedOut = 1")
    public List<OrderRecord> getAllCheckedOutOrder();

    // get all Un-checkout orders
    @Query("FROM OrderRecord WHERE checkedOut = 0")
    public List<OrderRecord> getAllUncheckedOutOrder();

    // get all checkout orders by Customer Email
    @Query("FROM OrderRecord WHERE checkedOut = 1 AND customerEmail = :cEmail")
    public List<OrderRecord> getAllCheckedOutOrderByEmail(@Param("cEmail") String cEmail);

    // get all Un-checkout orders by Customer Email
    @Query("FROM OrderRecord WHERE checkedOut = 0 AND customerEmail = :cEmail")
    public List<OrderRecord> getAllUncheckedOutOrderByEmail(@Param("cEmail") String cEmail);
}
