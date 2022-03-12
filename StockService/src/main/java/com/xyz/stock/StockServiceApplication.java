package com.xyz.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.xyz.stock.entity.ItemType;
import com.xyz.stock.entity.Item;
import com.xyz.stock.persistence.ItemDao;
import com.xyz.stock.persistence.ItemTypeDao;


@EntityScan(basePackages = "com.xyz.stock.entity")
@EnableJpaRepositories(basePackages = "com.xyz.stock.persistence")
@SpringBootApplication(scanBasePackages = "com.xyz.stock")
public class StockServiceApplication /*implements CommandLineRunner*/ {

	//@Autowired
	//ItemDao itemDoa;
	
//	@Autowired
//	ItemTypeDao itemTypeDoa;
	
	public static void main(String[] args) {
		SpringApplication.run(StockServiceApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		ItemType itemType1 = new ItemType("Books", 0);
//		ItemType itemType2 = new ItemType("CD", 10);
//		ItemType itemType3 = new ItemType("Cosmetics", 12);
//		
//		itemTypeDoa.save(itemType1);
//		itemTypeDoa.save(itemType2);
//		itemTypeDoa.save(itemType3);
//		
//		Item item01 = new Item(1001, "Book 1", itemType1, 10, 3);
//		Item item02 = new Item(1002, "Book 2", itemType1, 15, 1000);
//		Item item03 = new Item(1003, "Book 3", itemType1, 20, 1000);
//		Item item04 = new Item(1004, "Book 4", itemType1, 25, 1000);
//		Item item05 = new Item(1005, "Book 5", itemType1, 30, 1000);
//		Item item06 = new Item(1006, "Book 6", itemType1, 35, 1000);
//		Item item07 = new Item(1007, "Book 7", itemType1, 40, 1000);
//		Item item08 = new Item(1008, "Book 8", itemType1, 13, 1000);
//		Item item09 = new Item(1009, "Book 9", itemType1, 27, 1000);
//		Item item10 = new Item(1010, "Book 10", itemType1, 39, 40);
//		
//		
//		Item item11 = new Item(2001, "CD 1", itemType2, 9, 5);
//		Item item12 = new Item(2002, "CD 2", itemType2, 13, 1000);
//		Item item13 = new Item(2003, "CD 3", itemType2, 14, 1000);
//		Item item14 = new Item(2004, "CD 4", itemType2, 17, 1000);
//		Item item15 = new Item(2005, "CD 5", itemType2, 16, 1000);
//		Item item16 = new Item(2006, "CD 6", itemType2, 15, 1000);
//		Item item17 = new Item(2007, "CD 7", itemType2, 11, 1000);
//		Item item18 = new Item(2008, "CD 8", itemType2, 19, 1000);
//		Item item19 = new Item(2009, "CD 9", itemType2, 14, 1000);
//		Item item20 = new Item(2010, "CD 10", itemType2, 21, 20);
//		
//		
//		Item item21 = new Item(3001, "Cosmetic 1", itemType3, 45, 7);
//		Item item22 = new Item(3002, "Cosmetic 2", itemType3, 49, 1000);
//		Item item23 = new Item(3003, "Cosmetic 3", itemType3, 35, 1000);
//		Item item24 = new Item(3004, "Cosmetic 4", itemType3, 39, 1000);
//		Item item25 = new Item(3005, "Cosmetic 5", itemType3, 49, 1000);
//		Item item26 = new Item(3006, "Cosmetic 6", itemType3, 59, 1000);
//		Item item27 = new Item(3007, "Cosmetic 7", itemType3, 49, 1000);
//		Item item28 = new Item(3008, "Cosmetic 8", itemType3, 29, 1000);
//		Item item29 = new Item(3009, "Cosmetic 9", itemType3, 99, 1000);
//		Item item30 = new Item(3010, "Cosmetic 10", itemType3, 50, 30);
//		
//		itemDoa.save(item01);
//		itemDoa.save(item02);
//		itemDoa.save(item03);
//		itemDoa.save(item04);
//		itemDoa.save(item05);
//		itemDoa.save(item06);
//		itemDoa.save(item07);
//		itemDoa.save(item08);
//		itemDoa.save(item09);
//		itemDoa.save(item10);
//		
//		itemDoa.save(item11);
//		itemDoa.save(item12);
//		itemDoa.save(item13);
//		itemDoa.save(item14);
//		itemDoa.save(item15);
//		itemDoa.save(item16);
//		itemDoa.save(item17);
//		itemDoa.save(item18);
//		itemDoa.save(item19);
//		itemDoa.save(item20);
//		
//		itemDoa.save(item21);
//		itemDoa.save(item22);
//		itemDoa.save(item23);
//		itemDoa.save(item24);
//		itemDoa.save(item25);
//		itemDoa.save(item26);
//		itemDoa.save(item27);
//		itemDoa.save(item28);
//		itemDoa.save(item29);
//		itemDoa.save(item30);
//	}

}
