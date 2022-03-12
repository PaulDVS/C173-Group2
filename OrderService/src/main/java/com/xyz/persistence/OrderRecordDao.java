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

    @Query("SELECT customerEmail FROM OrderRecord WHERE orderId=:oId")
    public String findCustomerEmailByOrderId(@Param("oId") int orderId);

    @Query("FROM OrderRecord WHERE customerEmail =: cEmail")
    public List<OrderRecord> findOrderByCustomerEmail(@Param("cEmail") String cEmail);

    @Transactional
	@Modifying
	@Query("UPDATE FROM OrderRecord SET checkedOut = 1 WHERE OrderId =: oId")
    public OrderRecord checkOutOrderById(@Param("oId") int OrderId);
}
