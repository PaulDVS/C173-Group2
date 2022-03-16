package com.xyz.orderServiceApp;


import java.util.Arrays;

import com.xyz.entity.BasketItem;
import com.xyz.entity.OrderRecord;
import com.xyz.persistence.BasketItemDao;
import com.xyz.persistence.OrderRecordDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages = "com.xyz.persistence")
@EntityScan(basePackages = "com.xyz.entity")
@SpringBootApplication(scanBasePackages = "com.xyz")
public class OrderServiceApplication /*implements CommandLineRunner*/ {

//	 @Autowired
//	 OrderRecordDao orderRecordDao;
	
	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	 // test data
//	 @Override
//	 public void run(String... args) throws Exception {
//		
//
//	 	BasketItem bas1 = new BasketItem(1, 1, 1);
//	 	BasketItem bas2 = new BasketItem(2, 2, 2);
//	 	BasketItem bas3 = new BasketItem(3, 2, 4);
//	 	BasketItem bas4 = new BasketItem(4, 3, 1);
//	 	OrderRecord ord1 = new OrderRecord(1, "suraiya@gmail.com", false, Arrays.asList(bas1, bas2));
//	 	OrderRecord ord2 = new OrderRecord(2, "jozef@gmail.com", true, Arrays.asList(bas3, bas4));
//
//	 	orderRecordDao.save(ord1);
//	 	orderRecordDao.save(ord2);
//	 }

}