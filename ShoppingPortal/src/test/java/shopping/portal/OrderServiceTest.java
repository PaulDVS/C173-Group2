package shopping.portal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.xyz.entities.BasketItem;
import com.xyz.entities.Item;
import com.xyz.entities.ItemType;
import com.xyz.entities.OrderRecord;
import com.xyz.entities.OrderRecords;
import com.xyz.entities.ResultImp;
import com.xyz.service.OrderService;
import com.xyz.service.OrderServiceImpl;
import com.xyz.shopping.portal.ShoppingPortalApplication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootTest(classes = ShoppingPortalApplication.class)
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

	@Mock
	RestTemplate restTemplate;
	
	@Mock
	ResponseEntity reMock;
	
	@Mock
	ResponseEntity reMock2;
	
	@InjectMocks
	OrderService orderService = new OrderServiceImpl();
	
//showCart() Testing
	
	//Showing userCart with basketItems
	@Test
	void t001() {
		String currentUserEmail = "Paul@Wiley.com";
	 	BasketItem bas1 = new BasketItem(1, 1003, 1);
	 	BasketItem bas2 = new BasketItem(2, 3006, 2);
		
		OrderRecord orderRecord = new OrderRecord(1, currentUserEmail, false, Arrays.asList(bas1, bas2));
		List<OrderRecord> list =new ArrayList<OrderRecord>();
		list.add(orderRecord);
		OrderRecords currentOrdersList = new OrderRecords(list);
		
		when(restTemplate.getForObject("http://localhost:8082/Orders/Unconfirmed/"+currentUserEmail, OrderRecords.class)).thenReturn(currentOrdersList);
	
		ItemType itemType1 = new ItemType("Books", 0);
		Item item1 = new Item(1003, "Book 3", itemType1, 20, 692);
		
		ItemType itemType3 = new ItemType("Cosmetics", 12);
		Item item2 = new Item(3006, "Cosmetic 6", itemType3, 59, 1000);
		
		when(restTemplate.getForObject("http://localhost:8081/Items/Id/"+bas1.getItemId(), Item.class)).thenReturn(item1);
		when(restTemplate.getForObject("http://localhost:8081/Items/Id/"+bas2.getItemId(), Item.class)).thenReturn(item2);
		
		float quantity = 2;
		float price = 59;
		float tax = 12;
		float testFinalPrice = (quantity*price) + ((quantity*price)*(tax/100));
				
		assertThat(orderService.showCart(currentUserEmail).get(1).getBasketItemFinalPrice()).isEqualTo(testFinalPrice);
	}
	
	//Showing Usercart with no basketItems
	@Test
	void t002() {
		String currentUserEmail = "Mohsen@Wiley.com";
		
		List<OrderRecord> list =new ArrayList<OrderRecord>();
		OrderRecords currentOrdersList = new OrderRecords(list);
		
		List<OrderRecord> resultList =new ArrayList<OrderRecord>();
		
		when(restTemplate.getForObject("http://localhost:8082/Orders/Unconfirmed/"+currentUserEmail, OrderRecords.class)).thenReturn(currentOrdersList);
		assertThat(orderService.showCart(currentUserEmail)).isEqualTo(resultList);
	}
	
	
//addItem() Testing
	
	//Adding item when user has an existing orderRecord
	@Test
	void t003() {
		String currentUserEmail = "Paul@Wiley.com";
	 	BasketItem bas1 = new BasketItem(1, 1003, 1);
	 	BasketItem bas2 = new BasketItem(2, 3006, 2);
		
		OrderRecord orderRecord = new OrderRecord(5, currentUserEmail, false, Arrays.asList(bas1, bas2));
		List<OrderRecord> list =new ArrayList<OrderRecord>();
		list.add(orderRecord);
		OrderRecords currentOrdersList = new OrderRecords(list);
		
		when(restTemplate.getForObject("http://localhost:8082/Orders/Unconfirmed/"+currentUserEmail, OrderRecords.class)).thenReturn(currentOrdersList);
	
		
		HttpHeaders header = new HttpHeaders();
		HttpEntity<OrderRecord> entity = new HttpEntity<OrderRecord>(header);
		
		OrderRecord res = new OrderRecord();
		
		ResultImp<OrderRecord> result = new ResultImp<OrderRecord>();
		result.setMessage("The Item(s) is/are added Successfully");
		result.setObject(res);

		when(restTemplate.exchange("http://localhost:8082/Orders/Items/Add/5/3007/10", HttpMethod.POST, entity, ResultImp.class))
		.thenReturn(reMock);
		when(reMock.getBody()).thenReturn(result);

		String returnMessage ="The Item(s) is/are added Successfully";
		
		
		ItemType itemType3 = new ItemType("Cosmetics", 12);
		Item itemResult = new Item(3007, "Cosmetic 7", itemType3, 49, 1000);
		
		when(restTemplate.getForObject("http://localhost:8081/Items/Id/"+3007, Item.class)).thenReturn(itemResult);
		
		assertThat(orderService.addItem(currentUserEmail, 3007, 10)).isEqualTo(returnMessage);
	}
	
	//Adding item when user doesn't have an existing orderRecord
	@Test
	void t004() {
		String currentUserEmail = "Colman@Wiley.com";
		
		List<OrderRecord> list =new ArrayList<OrderRecord>();
		OrderRecords currentOrdersList = new OrderRecords(list);

		when(restTemplate.getForObject("http://localhost:8082/Orders/Unconfirmed/"+currentUserEmail, OrderRecords.class)).thenReturn(currentOrdersList);
		
		OrderRecord orderRecord = new OrderRecord(7, currentUserEmail, false, null);
		when(restTemplate.postForEntity("http://localhost:8082/Orders/Create/"+currentUserEmail, null, OrderRecord.class)).thenReturn(reMock);
		when(reMock.getBody()).thenReturn(orderRecord);
		
		
		HttpHeaders header = new HttpHeaders();
		HttpEntity<OrderRecord> entity = new HttpEntity<OrderRecord>(header);
		
		OrderRecord res = new OrderRecord();
		
		ResultImp<OrderRecord> result = new ResultImp<OrderRecord>();
		result.setMessage("The Item(s) is/are added Successfully");
		result.setObject(res);
		
		
		when(restTemplate.exchange("http://localhost:8082/Orders/Items/Add/7/3007/10", HttpMethod.POST, entity, ResultImp.class))
		.thenReturn(reMock2);
		when(reMock2.getBody()).thenReturn(result);
		
		
		String returnMessage ="The Item(s) is/are added Successfully";
		
		ItemType itemType3 = new ItemType("Cosmetics", 12);
		Item itemResult = new Item(3007, "Cosmetic 7", itemType3, 49, 1000);
		
		when(restTemplate.getForObject("http://localhost:8081/Items/Id/"+3007, Item.class)).thenReturn(itemResult);
		
		assertThat(orderService.addItem(currentUserEmail, 3007, 10)).isEqualTo(returnMessage);
	}
	
	
//removeItem() Testing
	
	//Remove Item from orderRecord
	@Test
	void t005() {
		String currentUserEmail = "Paul@Wiley.com";
	 	BasketItem bas1 = new BasketItem(1, 1003, 1);
	 	BasketItem bas2 = new BasketItem(2, 3006, 2);
		
		OrderRecord orderRecord = new OrderRecord(5, currentUserEmail, false, Arrays.asList(bas1, bas2));
		List<OrderRecord> list =new ArrayList<OrderRecord>();
		list.add(orderRecord);
		OrderRecords currentOrdersList = new OrderRecords(list);
		
		when(restTemplate.getForObject("http://localhost:8082/Orders/Unconfirmed/"+currentUserEmail, OrderRecords.class)).thenReturn(currentOrdersList);
	
		HttpHeaders header = new HttpHeaders();
		HttpEntity<OrderRecord> entity = new HttpEntity<OrderRecord>(header);
		
		OrderRecord res = new OrderRecord();
		
		ResultImp<OrderRecord> result = new ResultImp<OrderRecord>();
		result.setMessage("The Item(s) is/are removed Successfully");
		result.setObject(res);

		when(restTemplate.exchange("http://localhost:8082/Orders/Items/Remove/5/1", HttpMethod.POST, entity, ResultImp.class))
		.thenReturn(reMock);
		when(reMock.getBody()).thenReturn(result);
		
		String returnMessage ="The Item(s) is/are removed Successfully";
		
		ItemType itemType1 = new ItemType("Books", 0);
		Item itemResult = new Item(1003, "Book 3", itemType1, 20, 1000);
		
		when(restTemplate.getForObject("http://localhost:8081/Items/Id/"+1003, Item.class)).thenReturn(itemResult);
		
		assertThat(orderService.removeItem(currentUserEmail, 1)).isEqualTo(returnMessage);
	}
		
	//Remove an item when that Item is not in user orderRecord
	@Test
	void t006() {
		String currentUserEmail = "Paul@Wiley.com";
	 	BasketItem bas1 = new BasketItem(1, 1003, 1);
	 	BasketItem bas2 = new BasketItem(2, 3006, 2);
		
		OrderRecord orderRecord = new OrderRecord(5, currentUserEmail, false, Arrays.asList(bas1, bas2));
		List<OrderRecord> list =new ArrayList<OrderRecord>();
		list.add(orderRecord);
		OrderRecords currentOrdersList = new OrderRecords(list);
		
		when(restTemplate.getForObject("http://localhost:8082/Orders/Unconfirmed/"+currentUserEmail, OrderRecords.class)).thenReturn(currentOrdersList);
	
		HttpHeaders header = new HttpHeaders();
		HttpEntity<OrderRecord> entity = new HttpEntity<OrderRecord>(header);
		
		OrderRecord res = new OrderRecord();
		
		ResultImp<OrderRecord> result = new ResultImp<OrderRecord>();
		result.setMessage("The Item(s) is/are removed Successfully");
		result.setObject(res);

		when(restTemplate.exchange("http://localhost:8082/Orders/Items/Remove/5/8", HttpMethod.POST, entity, ResultImp.class))
		.thenReturn(reMock);
		when(reMock.getBody()).thenReturn(result);
		
		String returnMessage ="Error item not removed Successfully";
		
		Item itemResult = new Item();
		
		when(restTemplate.getForObject("http://localhost:8081/Items/Id/"+0, Item.class)).thenReturn(itemResult);
		
		assertThat(orderService.removeItem(currentUserEmail, 8)).isEqualTo(returnMessage);
	}	

		
}
