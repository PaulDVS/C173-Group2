package com.xyz;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.security.auth.message.config.AuthConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.xyz.entity.BasketItem;
import com.xyz.entity.BasketItems;
import com.xyz.entity.OrderRecord;
import com.xyz.orderServiceApp.OrderServiceApplication;
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

@SpringBootTest(classes=OrderServiceApplication.class)
//@ContextConfiguration(classes = AuthConfig.class)
@ExtendWith(MockitoExtension.class)
public class OrderServiceApplicationTests {
	
	@Mock
	private BasketItemDao basketItemDao;
	
	@Mock
	OrderRecordDao orderRecordDao;
	
	@InjectMocks
	private OrderService orderService = new OrderServiceImpl();


	// Jozef's Tests_____________________________________________________________________________________________________________________________________________________________

	// @Test
	// public void test1() {
	// 	System.out.println("test1");
	// }
	
	
	// // Checks that a created order can be successfully retrieved.
	// @Test
	// public void test2() {
	// 	boolean orderExists = false;
		
	// 	OrderRecord orderRecordd=new OrderRecord(0, "Jozef@Wiley.com",false, null);
		
	// 	when(orderRecordDao.save(orderRecordd)).thenReturn(orderRecordd);

	// 	OrderRecord orderRecord = orderService.createOrderRecord("Jozef@Wiley.com");
	// 	assertThat(orderRecord.isCheckedOut()).isFalse();
		
	// 	BasketItem item = new BasketItem(12, 1001, 5);
	// 	BasketItem item2 = new BasketItem(13, 1002, 10);
		
	// 	List<BasketItem>basketItems = Arrays.asList(
	// 	 item,
	// 	 item2
	// 	);
		
	// 	orderRecord.setItems(basketItems);
		
		
		
		
	// 	orderService.addNewItemToOrder(Optional.of(orderRecord), item);
	// 	orderService.addNewItemToOrder(Optional.of(orderRecord), item2);
		
	// 	for(OrderRecord record : orderService.getAllOrderRecords()) {
	// 		if(record.getOrderId() == orderRecord.getOrderId()) {
	// 			orderExists = true;
	// 		}
	// 	}
		
		
	// }
	
	// // Checks that items added to an order can be retrieved.
	// @Test
	// public void test3() {
	// 	boolean itemsExist = false;
		
	// 	OrderRecord orderRecord= orderService.createOrderRecord("jozef@gmail.com");
	// 	BasketItem item = new BasketItem(12, 1001, 5);
	// 	BasketItem item2 = new BasketItem(13, 1002, 10);
	// 	orderService.addNewItemToOrder(Optional.of(orderRecord), item);
	// 	orderService.addNewItemToOrder(Optional.of(orderRecord), item2);
		
	// 	for(OrderRecord record : orderService.getAllOrderRecords()) {
	// 		if(record.getOrderId() == orderRecord.getOrderId()) {
	// 			if(record.getItems().contains(item) && record.getItems().contains(item2)) {
	// 				itemsExist = true;
	// 			}
				
	// 		}
	// 	}
		
	// 	assertThat(itemsExist);
	// }
	
	// // Checks that items can be removed from an order
	// @Test
	// public void test4() throws EditCheckedOutException {
	// 	boolean itemsExist = false;
	// 	boolean itemRemovedSuccessfully = false;
		
	// 	OrderRecord orderRecord= orderService.createOrderRecord("jozef@gmail.com");
	// 	BasketItem item = new BasketItem(12, 1001, 5);
	// 	BasketItem item2 = new BasketItem(13, 1002, 10);
	// 	orderService.addNewItemToOrder(Optional.of(orderRecord), item);
	// 	orderService.addNewItemToOrder(Optional.of(orderRecord), item2);
		
	// 	for(OrderRecord record : orderService.getAllOrderRecords()) {
	// 		if(record.getOrderId() == orderRecord.getOrderId()) {
	// 			if(record.getItems().contains(item) && record.getItems().contains(item2)) {
	// 				itemsExist = true;
	// 				List<Integer> itemIds = new ArrayList<>();
	// 				itemIds.add(item.getItemId());
	// 				orderService.removeBasketItemsFromOrder(orderRecord.getOrderId(), itemIds);
	// 				for(OrderRecord updRecord : orderService.getAllOrderRecords()) {
	// 					if(updRecord.getOrderId() == record.getOrderId()) {
	// 						itemRemovedSuccessfully = !updRecord.getItems().contains(item);
	// 					}
	// 				}
	// 			}
				
	// 		}
	// 	}
		
	// 	assertThat(itemsExist && itemRemovedSuccessfully);
	// }
	
	
	// // Checks that item quantity can be changed on an order
	// @Test
	// public void test5() throws EditCheckedOutException {
	// 	boolean itemsExist = false;
	// 	boolean quantityChangedSuccessfully = false;
		
	// 	OrderRecord orderRecord= orderService.createOrderRecord("jozef@gmail.com");
	// 	BasketItem item = new BasketItem(12, 1001, 5);
	// 	BasketItem item2 = new BasketItem(13, 1002, 10);
	// 	orderService.addNewItemToOrder(Optional.of(orderRecord), item);
	// 	orderService.addNewItemToOrder(Optional.of(orderRecord), item2);
		
	// 	for(OrderRecord record : orderService.getAllOrderRecords()) {
	// 		if(record.getOrderId() == orderRecord.getOrderId()) {
	// 			if(record.getItems().contains(item) && record.getItems().contains(item2)) {
	// 				itemsExist = true;
	// 				List<Integer> itemIds = new ArrayList<>();
	// 				itemIds.add(item.getItemId());
	// 				List<Integer> quantities = new ArrayList<>();
	// 				quantities.add(3);
	// 				orderService.removeBasketItemsFromOrderByQuantity(record.getOrderId(), itemIds, quantities);
	// 				for(OrderRecord updRecord : orderService.getAllOrderRecords()) {
	// 					if(updRecord.getOrderId() == record.getOrderId()) {
	// 						for(BasketItem updItem : updRecord.getItems()) {
	// 							quantityChangedSuccessfully = updItem.getQuantity() == 2;
	// 						}
	// 					}
	// 				}
	// 			}
				
	// 		}
	// 	}
		
	// 	assertThat(itemsExist && quantityChangedSuccessfully);
	// }


	// _____________________________________________________________________________________________________________________________________________________________________________

	// Check List<OrderRecord> getAllOrderRecords()
	@Test
	void Test1(){
		OrderRecord[] orderRecordList = new OrderRecord[]{new OrderRecord(1, "test", false, new ArrayList<BasketItem>()),
		new OrderRecord(2, "something", false, new ArrayList<BasketItem>()),
		new OrderRecord(3, "something", false, new ArrayList<BasketItem>()) };

		when(orderRecordDao.findAll()).thenReturn(Arrays.asList(orderRecordList));

		// Checks get all orders returns orders correctly
		assertEquals(orderService.getAllOrderRecords(), Arrays.asList(orderRecordList));
		// Checks get all orders returns right size
		assertEquals(orderService.getAllOrderRecords().size(), 3);
	}

	// Check OrderRecord findOrderByOrderId(int orderId) 
	@Test
	void Test2(){
		OrderRecord orderRecord = new OrderRecord(1, "test", false, null);
		Optional<OrderRecord> test = Optional.of(orderRecord);
		
		when(orderRecordDao.findById(1)).thenReturn(test);

		// Check if findOrderByOrderId returns correct Order by Id
		assertEquals(orderService.findOrderByOrderId(1), orderRecord);
	}

	// Check String findCustomerEmailByOrderId(int orderId)
	@Test
	void Test3(){
		OrderRecord orderRecord = new OrderRecord(1, "test@email.com", false, null);

		when(orderRecordDao.findCustomerEmailByOrderId(orderRecord.getOrderId())).thenReturn(orderRecord.getCustomerEmail());

		// Check if find customer email by order Id return correct email String.
		assertEquals(orderService.findCustomerEmailByOrderId(1), "test@email.com");
	}
	
	// Check List<OrderRecord> findOrdersByCustomerEmail(String cEmail)
	@Test
	void Test4(){
		OrderRecord[] wrongResult = new OrderRecord[]{new OrderRecord(1, "colman", false, new ArrayList<BasketItem>()),
		new OrderRecord(2, "mohsen", false, new ArrayList<BasketItem>())};

		OrderRecord[] result = new OrderRecord[]{new OrderRecord(1, "colman", false, new ArrayList<BasketItem>()),
		new OrderRecord(2, "colman", false, new ArrayList<BasketItem>())};

		when(orderRecordDao.findOrderByCustomerEmail("colman")).thenReturn(Arrays.asList(result));

		// Check if find orders by customer email returns the right customer list.
		assertEquals(orderService.findOrdersByCustomerEmail("colman"), Arrays.asList(result));
		// Check if find orders by customer email returns list with right size
		assertEquals(orderService.findOrdersByCustomerEmail("colman").size(), 2);
		// Check if other customer emails are returned
		assertNotEquals(orderService.findOrdersByCustomerEmail("colman"), wrongResult);
	}

	// Check OrderRecord createOrderRecord(String cEmail)
	@Test
	public void Test5() {
		OrderRecord orderRecord = new OrderRecord(0, "Jozef@Wiley.com", false, null);
		when(orderRecordDao.save(orderRecord)).thenReturn(orderRecord);

		// Checks that a created order can be successfully retrieved.
		assertEquals(orderService.createOrderRecord("Jozef@Wiley.com"), orderRecord);
		// Checks that a created order has NOT checked out
		assertThat(orderService.createOrderRecord("Jozef@Wiley.com").isCheckedOut()).isFalse();
	}

	// Check OrderRecord confirmOrder(int OrderId)
	@Test
	public void Test6(){
		when(orderRecordDao.findById(0)).thenReturn(Optional.of(new OrderRecord(0, "Jozef@Wiley.com", false, null)));
		when(orderRecordDao.save(new OrderRecord(0, "Jozef@Wiley.com", true, null))).thenReturn(new OrderRecord(0, "Jozef@Wiley.com", true, null));
		// Check if it set order from False -> True
		assertThat(orderService.confirmOrder(0).isCheckedOut()).isTrue();

		when(orderRecordDao.findById(1)).thenReturn(Optional.of(new OrderRecord(1, "Mohsen@Wiley.com", true, null)));
		when(orderRecordDao.save(new OrderRecord(1, "Mohsen@Wiley.com", true, null))).thenReturn(new OrderRecord(1, "Mohsen@Wiley.com", true, null));
		// Check if it setting order from True -> True is still true
		assertThat(orderService.confirmOrder(1).isCheckedOut()).isTrue();

		// If Order does not exist, then confirming it just returns null
		when(orderRecordDao.findById(2)).thenReturn(Optional.empty());
		assertThat(orderService.confirmOrder(2)).isEqualTo(null);
	}

	// Check List<OrderRecord> getAllCheckedOutOrder()
	@Test
	public void Test7(){
		when(orderRecordDao.getAllCheckedOutOrder()).thenReturn(Arrays.asList(new OrderRecord(1, "Mohsen@Wiley.com", true, null),
		new OrderRecord(2, "Paul@Wiley.com", true, null), new OrderRecord(3, "Suraiya@Wiley.com", true, null)));

		// check get All checked out List has correct Size
		assertThat(orderService.getAllCheckedOutOrder().size()).isEqualTo(3);

		// check get All checked out Order doesn't not return Unchecked out
		assertThat(orderService.getAllCheckedOutOrder()).isNotEqualTo(Arrays.asList(new OrderRecord(1, "Mohsen@Wiley.com", false, null),
		new OrderRecord(2, "Paul@Wiley.com", false, null), new OrderRecord(3, "Suraiya@Wiley.com", false, null)));
	}

	// Check List<OrderRecord> getAllUncheckedOutOrder()
	@Test
	public void Test8(){
		when(orderRecordDao.getAllUncheckedOutOrder()).thenReturn(Arrays.asList(new OrderRecord(1, "Mohsen@Wiley.com", false, null),
		new OrderRecord(2, "Paul@Wiley.com", false, null), new OrderRecord(3, "Suraiya@Wiley.com", false, null)));

		// check get All checked out List has correct Size
		assertThat(orderService.getAllUncheckedOutOrder().size()).isEqualTo(3);

		// check get All checked out Order doesn't not return checked out
		assertThat(orderService.getAllUncheckedOutOrder()).isNotEqualTo(Arrays.asList(new OrderRecord(1, "Mohsen@Wiley.com", true, null),
		new OrderRecord(2, "Paul@Wiley.com", true, null), new OrderRecord(3, "Suraiya@Wiley.com", true, null)));
	}

	// List<OrderRecord> getAllCheckedOutOrderByEmail(String cEmail)
	@Test
	public void Test9(){
		when(orderRecordDao.getAllCheckedOutOrderByEmail("Mohsen@Wiley.com")).thenReturn(Arrays.asList(new OrderRecord(1, "Mohsen@Wiley.com", true, null),
		new OrderRecord(2, "Mohsen@Wiley.com", true, null), new OrderRecord(3, "Mohsen@Wiley.com", true, null)));

		// check get All checked out List has correct Size
		assertThat(orderService.getAllCheckedOutOrderByEmail("Mohsen@Wiley.com").size()).isEqualTo(3);

		// check get All checked out List does not contain Emails of other User
		assertThat(orderService.getAllCheckedOutOrderByEmail("Mohsen@Wiley.com")).isNotEqualTo(Arrays.asList(new OrderRecord(1, "Suraiya@Wiley.com", true, null),
		new OrderRecord(2, "Suraiya@Wiley.com", true, null), new OrderRecord(3, "Suraiya@Wiley.com", true, null)));

		// check get All checked out List does not contain Un Checked out orders
		assertThat(orderService.getAllCheckedOutOrderByEmail("Mohsen@Wiley.com")).isNotEqualTo(Arrays.asList(new OrderRecord(1, "Mohsen@Wiley.com", false, null),
		new OrderRecord(2, "Mohsen@Wiley.com", false, null), new OrderRecord(3, "Mohsen@Wiley.com", false, null)));
	}

	// List<OrderRecord> getAllUncheckedOutOrderByEmail(String cEmail)
	@Test
	public void Test10(){
		when(orderRecordDao.getAllUncheckedOutOrderByEmail("Mohsen@Wiley.com")).thenReturn(Arrays.asList(new OrderRecord(1, "Mohsen@Wiley.com", false, null),
		new OrderRecord(2, "Mohsen@Wiley.com", false, null), new OrderRecord(3, "Mohsen@Wiley.com", false, null)));

		// check get All Un checked out List has correct Size
		assertThat(orderService.getAllUncheckedOutOrderByEmail("Mohsen@Wiley.com").size()).isEqualTo(3);

		// check get All Un checked out List does not contain Emails of other User
		assertThat(orderService.getAllUncheckedOutOrderByEmail("Mohsen@Wiley.com")).isNotEqualTo(Arrays.asList(new OrderRecord(1, "Suraiya@Wiley.com", false, null),
		new OrderRecord(2, "Suraiya@Wiley.com", false, null), new OrderRecord(3, "Suraiya@Wiley.com", false, null)));

		// check get All Un checked out List does not contain Checked out orders
		assertThat(orderService.getAllUncheckedOutOrderByEmail("Mohsen@Wiley.com")).isNotEqualTo(Arrays.asList(new OrderRecord(1, "Mohsen@Wiley.com", true, null),
		new OrderRecord(2, "Mohsen@Wiley.com", true, null), new OrderRecord(3, "Mohsen@Wiley.com", true, null)));
	}

	// Check OrderRecord addBasketItemsToOrder(int orderId, List<Integer> itemIds, List<Integer> quantities)
	@Test
	public void Test11() throws EditCheckedOutException{
		// check adding to an Order Id that does not exist.
		when(orderRecordDao.findById(10)).thenReturn(Optional.empty());
		List<Integer> dummyList = Arrays.asList(new Integer[]{1});
		assertThat(orderService.addBasketItemsToOrder(10, dummyList, dummyList)).isEqualTo(null);

		// check adding to an Order Id that is already checked out
		when(orderRecordDao.findById(0)).thenReturn(Optional.of(new OrderRecord(0, "Jozef@Wiley.com", true, null)));
		Throwable exception = assertThrows(EditCheckedOutException.class, () -> orderService.addBasketItemsToOrder(0, dummyList, dummyList));
    	assertEquals("The order has already been confirmed.", exception.getMessage());

		// check adding order to an order Id unchecked out
		OrderRecord before = new OrderRecord(1, "Paul@Wiley.com", false, Arrays.asList(new BasketItem[]{
			new BasketItem(1, 1, 1),
			new BasketItem(2, 2, 1),
			new BasketItem(3, 3, 1)
		}));

		assertThat(before.getItems().get(0).getQuantity()).isEqualTo(1);

		OrderRecord after = new OrderRecord(1, "Paul@Wiley.com", false, Arrays.asList(new BasketItem[]{
			new BasketItem(1, 1, 2),
			new BasketItem(2, 2, 1),
			new BasketItem(3, 3, 1)
		}));

		when(orderRecordDao.findById(1)).thenReturn(Optional.of(before));
		when(orderRecordDao.save(after)).thenReturn(after);

		OrderRecord result = orderService.addBasketItemsToOrder(1, dummyList, dummyList);
		
		assertThat(result.getItems().get(0).getItemId()).isEqualTo(1);
		assertThat(result.getItems().get(0).getQuantity()).isEqualTo(2);
	}
}
