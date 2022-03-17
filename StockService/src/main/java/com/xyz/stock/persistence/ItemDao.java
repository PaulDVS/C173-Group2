package com.xyz.stock.persistence;

import java.util.List;

import org.springframework.data.annotation.Persistent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xyz.stock.entity.Item;
import com.xyz.stock.entity.ItemType;

@Persistent
public interface ItemDao extends JpaRepository<Item, Integer> {

	@Query("from Item where type = :itemType")
	public List<Item> findItemsByItemType(@Param("itemType") ItemType itemType);
	
	@Query("SELECT price from Item where id = :itemId")
	public float getPriceById(@Param("itemId") int itemId);
	
	@Query("SELECT quantity from Item where id = :itemId")
	public int getStockQuantityById(@Param ("itemId") int itemId);
	
}
