package com.xyz;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.security.auth.message.config.AuthConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.xyz.entity.BasketItem;
import com.xyz.entity.BasketItems;
import com.xyz.entity.OrderRecord;
import com.xyz.persistence.BasketItemDao;
import com.xyz.persistence.OrderRecordDao;
import com.xyz.service.EditCheckedOutException;
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
	
	
	// Checks that a created order can be successfully retrieved.
	@Test
	public void test2() {
		boolean orderExists = false;
		OrderRecord orderRecord= orderService.createOrderRecord("jozef@gmail.com");
		BasketItem item = new BasketItem(12, 1001, 5);
		BasketItem item2 = new BasketItem(13, 1002, 10);
		orderService.addNewItemToOrder(Optional.of(orderRecord), item);
		orderService.addNewItemToOrder(Optional.of(orderRecord), item2);
		for(OrderRecord record : orderService.getAllOrderRecords()) {
			if(record.getOrderId() == orderRecord.getOrderId()) {
				orderExists = true;
			}
		}
		
		assertThat(orderExists);
	}
	
	// Checks that items added to an order can be retrieved.
	@Test
	public void test3() {
		boolean itemsExist = false;
		OrderRecord orderRecord= orderService.createOrderRecord("jozef@gmail.com");
		BasketItem item = new BasketItem(12, 1001, 5);
		BasketItem item2 = new BasketItem(13, 1002, 10);
		orderService.addNewItemToOrder(Optional.of(orderRecord), item);
		orderService.addNewItemToOrder(Optional.of(orderRecord), item2);
		for(OrderRecord record : orderService.getAllOrderRecords()) {
			if(record.getOrderId() == orderRecord.getOrderId()) {
				if(record.getItems().contains(item) && record.getItems().contains(item2)) {
					itemsExist = true;
				}
				
			}
		}
		
		assertThat(itemsExist);
	}
	
	// Checks that items can be removed from an order
	@Test
	public void test4() throws EditCheckedOutException {
		boolean itemsExist = false;
		boolean itemRemovedSuccessfully = false;
		OrderRecord orderRecord= orderService.createOrderRecord("jozef@gmail.com");
		BasketItem item = new BasketItem(12, 1001, 5);
		BasketItem item2 = new BasketItem(13, 1002, 10);
		orderService.addNewItemToOrder(Optional.of(orderRecord), item);
		orderService.addNewItemToOrder(Optional.of(orderRecord), item2);
		for(OrderRecord record : orderService.getAllOrderRecords()) {
			if(record.getOrderId() == orderRecord.getOrderId()) {
				if(record.getItems().contains(item) && record.getItems().contains(item2)) {
					itemsExist = true;
					List<Integer> itemIds = new ArrayList<>();
					itemIds.add(item.getItemId());
					orderService.removeBasketItemsFromOrder(orderRecord.getOrderId(), itemIds);
					for(OrderRecord updRecord : orderService.getAllOrderRecords()) {
						if(updRecord.getOrderId() == record.getOrderId()) {
							itemRemovedSuccessfully = !updRecord.getItems().contains(item);
						}
					}
				}
				
			}
		}
		
		assertThat(itemsExist && itemRemovedSuccessfully);
	}
	
	
	// Checks that item quantity can be changed on an order
	@Test
	public void test5() throws EditCheckedOutException {
		boolean itemsExist = false;
		boolean quantityChangedSuccessfully = false;
		OrderRecord orderRecord= orderService.createOrderRecord("jozef@gmail.com");
		BasketItem item = new BasketItem(12, 1001, 5);
		BasketItem item2 = new BasketItem(13, 1002, 10);
		orderService.addNewItemToOrder(Optional.of(orderRecord), item);
		orderService.addNewItemToOrder(Optional.of(orderRecord), item2);
		for(OrderRecord record : orderService.getAllOrderRecords()) {
			if(record.getOrderId() == orderRecord.getOrderId()) {
				if(record.getItems().contains(item) && record.getItems().contains(item2)) {
					itemsExist = true;
					List<Integer> itemIds = new ArrayList<>();
					itemIds.add(item.getItemId());
					List<Integer> quantities = new ArrayList<>();
					quantities.add(3);
					orderService.removeBasketItemsFromOrderByQuantity(record.getOrderId(), itemIds, quantities);
					for(OrderRecord updRecord : orderService.getAllOrderRecords()) {
						if(updRecord.getOrderId() == record.getOrderId()) {
							for(BasketItem updItem : updRecord.getItems()) {
								quantityChangedSuccessfully = updItem.getQuantity() == 2;
							}
						}
					}
				}
				
			}
		}
		
		assertThat(itemsExist && quantityChangedSuccessfully);
	}

}
