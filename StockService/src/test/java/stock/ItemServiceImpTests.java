package stock;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xyz.stock.StockServiceApplication;
import com.xyz.stock.entity.Item;
import com.xyz.stock.entity.ItemType;
import com.xyz.stock.entity.StockItem;
import com.xyz.stock.persistence.ItemDao;
import com.xyz.stock.service.ItemService;
import com.xyz.stock.service.ItemServiceImp;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StockServiceApplication.class)
@ExtendWith(MockitoExtension.class)
class ItemServiceImpTests {

	@Mock
	private ItemDao itemDao;

	@InjectMocks
	private ItemService itemService = new ItemServiceImp();

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void testAddItem() {
		
		ItemType itemType = new ItemType("Books", 20000);
		Item item = new Item(9001, "TestItem", itemType, 13.0f, 2200);
		Item item1 = new Item(9002, "TestItem1", itemType, 14.0f, 2200);
		
		when(itemDao.save(item1)).thenReturn(item);
		assertThat(itemService.addItem(item1)).isEqualTo(item);
		
	}

	@Test
	final void testGetAllItems() {

		ItemType itemType1 = new ItemType("Books", 0);
		ItemType itemType2 = new ItemType("CD", 10);
		ItemType itemType3 = new ItemType("Cosmetics", 12);

		List<Item> expectedItemsList = Arrays.asList(

				new Item(1001, "Book 1", itemType1, 10, 3), new Item(1002, "Book 2", itemType1, 15, 1000),
				new Item(1003, "Book 3", itemType1, 20, 1000), new Item(1004, "Book 4", itemType1, 25, 1000),
				new Item(1005, "Book 5", itemType1, 30, 1000), new Item(1006, "Book 6", itemType1, 35, 1000),
				new Item(1007, "Book 7", itemType1, 40, 1000), new Item(1008, "Book 8", itemType1, 13, 1000),
				new Item(1009, "Book 9", itemType1, 27, 1000), new Item(1010, "Book 10", itemType1, 39, 40),
				
				new Item(2001, "CD 1", itemType2, 9, 5), new Item(2002, "CD 2", itemType2, 13, 1000),
				new Item(2003, "CD 3", itemType2, 14, 1000), new Item(2004, "CD 4", itemType2, 17, 1000),
				new Item(2005, "CD 5", itemType2, 16, 1000), new Item(2006, "CD 6", itemType2, 15, 1000),
				new Item(2007, "CD 7", itemType2, 11, 1000), new Item(2008, "CD 8", itemType2, 19, 1000),
				new Item(2009, "CD 9", itemType2, 14, 1000), new Item(2010, "CD 10", itemType2, 21, 20),

				new Item(3001, "Cosmetic 1", itemType3, 45, 7), new Item(3002, "Cosmetic 2", itemType3, 49, 1000),
				new Item(3003, "Cosmetic 3", itemType3, 35, 1000), new Item(3004, "Cosmetic 4", itemType3, 39, 1000),
				new Item(3005, "Cosmetic 5", itemType3, 49, 1000), new Item(3006, "Cosmetic 6", itemType3, 59, 1000),
				new Item(3007, "Cosmetic 7", itemType3, 49, 1000), new Item(3008, "Cosmetic 8", itemType3, 29, 1000),
				new Item(3009, "Cosmetic 9", itemType3, 99, 1000), new Item(3010, "Cosmetic 10", itemType3, 50, 30)
		);
		
		when(itemDao.findAll()).thenReturn(expectedItemsList);
		assertThat(itemService.getAllItems().getItems()).isEqualTo(expectedItemsList);
	}

	@Test
	final void testGetItemsByType() {
		
		ItemType itemType2 = new ItemType("CD", 10);
		List<Item> expectedItemsList = Arrays.asList(

				new Item(2001, "CD 1", itemType2, 9, 5), new Item(2002, "CD 2", itemType2, 13, 1000),
				new Item(2003, "CD 3", itemType2, 14, 1000), new Item(2004, "CD 4", itemType2, 17, 1000),
				new Item(2005, "CD 5", itemType2, 16, 1000), new Item(2006, "CD 6", itemType2, 15, 1000),
				new Item(2007, "CD 7", itemType2, 11, 1000), new Item(2008, "CD 8", itemType2, 19, 1000),
				new Item(2009, "CD 9", itemType2, 14, 1000), new Item(2010, "CD 10", itemType2, 21, 20)

		);
		
		when(itemDao.findItemsByItemType(itemType2)).thenReturn(expectedItemsList);
		assertThat(itemService.getItemsByType(itemType2).getItems()).isEqualTo(expectedItemsList);
	}

	@Test
	final void testGetItemById() {
		
		ItemType itemType2 = new ItemType("CD", 10);
		Item expectedItem = new Item(2001, "CD 1", itemType2, 9, 5);
		
		when(itemDao.getById(2001)).thenReturn(expectedItem);
		assertThat(itemService.getItemById(2001)).isEqualTo(expectedItem);
	}

	@Test
	final void testGetStockQuantityById() {
		
		int expectedQuantity = 5;
		
		when(itemDao.getStockQuantityById(2001)).thenReturn(expectedQuantity);
		assertThat(itemService.getStockQuantityById(2001)).isEqualTo(expectedQuantity);
	}

	@Test
	final void testGetPriceById() {
		
		float expectedPrice = 9;
		when(itemDao.getPriceById(2001)).thenReturn(expectedPrice);
		
		assertThat(itemService.getPriceById(2001)).isEqualTo(expectedPrice);
		
	}

	@Test
	final void testSetStockQuantityById() {
		
		//ItemType itemType2 = new ItemType("CD", 10);
		//StockItem expectedItem = new StockItem(2001,5);
		
		
		ItemService spy = Mockito.spy(itemService);
		
		// spy.setStockQuantityById(expectedItem);
		
		//assertEquals(Integer.MIN_VALUE, spy.setStockQuantityById(expectedItem));
		
		//assertThat(itemService.setStockQuantityById(expectedItem),expectedQuantity);
		//assertThat(itemService.setStockQuantityById(expectedItem)).isEqualTo(expectedQuantity);
	}

}
