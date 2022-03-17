package com.xyz.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.xyz.entities.User;
import com.xyz.persistence.UserDao;

@SpringBootApplication(scanBasePackages ="com.xyz")
@EntityScan(basePackages = "com.xyz.entities")
@EnableJpaRepositories(basePackages = "com.xyz.persistence")
public class AccountServiceApplication implements CommandLineRunner  {

	@Autowired
	UserDao userDao;
	
	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	//Some sample User initialisation
	
	@Override
	public void run(String... args) throws Exception {
		
//		User user1 = new User("Colman@Wiley.com", "0800 000 0001", "123 North Street", "Colman", "root");
//		User user2 = new User("Jozef@Wiley.com", "0800 000 0002", "10 West Avenue", "Jozef", "test123");
//		User user3 = new User("Mohsen@Wiley.com", "0800 000 0003", "5 South Drive", "Mohsen", "admin");
//		User user4 = new User("Paul@Wiley.com", "0800 000 0004", "19 East Lane", "Paul", "lychee");
//		User user5 = new User("Suraiya@Wiley.com", "0800 000 0005", "12 High Street", "Suraiya", "password");
//		
//		
//		userDao.save(user1);
//		userDao.save(user2);
//		userDao.save(user3);
//		userDao.save(user4);
//		userDao.save(user5);
		
	}
}
