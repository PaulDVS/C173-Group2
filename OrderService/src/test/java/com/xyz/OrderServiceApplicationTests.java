package com.xyz;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import javax.security.auth.message.config.AuthConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.xyz.persistence.BasketItemDao;
import com.xyz.persistence.OrderRecordDao;
import com.xyz.service.OrderService;
import com.xyz.service.OrderServiceImpl;

/*
	@BeforeAll
	@Test
	@BeforeEach
	@AfterEach
	@AfterAll
*/
@SpringBootTest
@ContextConfiguration(classes = AuthConfig.class)
@ExtendWith(MockitoExtension.class)
public class OrderServiceApplicationTests {
	
	@Mock
	private BasketItemDao basketItemDao;
	
	@Mock
	OrderRecordDao orderRecordDao;
	
	@InjectMocks
	private OrderService orderService = new OrderServiceImpl();

	@Test
	public void test1() {
		System.out.println("test1");
	}
	
	@Test
	public void test2() {
		
	}

}
