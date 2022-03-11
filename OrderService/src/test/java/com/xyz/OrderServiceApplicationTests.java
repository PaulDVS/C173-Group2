package com.xyz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.security.auth.message.config.AuthConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/*
	@BeforeAll
	@Test
	@BeforeEach
	@AfterEach
	@AfterAll
*/
@SpringBootTest
@ContextConfiguration(classes = AuthConfig.class) 
public class OrderServiceApplicationTests {

	@Test
	public void test1() {
		System.out.println("test1");
	}

}
