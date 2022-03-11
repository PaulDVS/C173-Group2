package com.xyz.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.xyz.stock.entity")
@EnableJpaRepositories(basePackages = "com.xyz.stock.persistence")
@SpringBootApplication(scanBasePackages = "com.xyz.stock")
public class StockServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockServiceApplication.class, args);
	}

}
