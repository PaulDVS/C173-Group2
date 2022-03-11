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
}
