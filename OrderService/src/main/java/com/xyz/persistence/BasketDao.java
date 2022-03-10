package com.xyz.persistence;

import java.util.List;

import com.xyz.entity.BasketItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketDao extends JpaRepository<BasketItem, Integer>{
    @Query("from Basket where customerEmail =: cEmail")
    public List<BasketItem> findBasketByCustomerEmail(@Param("cEmail") String cEmail);
}
