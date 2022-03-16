package stock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.xyz.stock.StockServiceApplication;
import com.xyz.stock.entity.ItemType;
import com.xyz.stock.entity.ItemTypes;
import com.xyz.stock.persistence.ItemTypeDao;
import com.xyz.stock.service.ItemTypeService;
import com.xyz.stock.service.ItemTypeServiceImp;


@SpringBootTest(classes=StockServiceApplication.class)
@ExtendWith(MockitoExtension.class)
class StockTypeServiceTests {

	@Mock
	private  ItemTypeDao itemTypeDao;

	@InjectMocks
	private  ItemTypeService itemTypeService = new ItemTypeServiceImp();
	
	@Test
	void addItem() {
		
		ItemType itemType = new ItemType("Paper",1.9f);
		when(itemTypeDao.save(itemType)).thenReturn(itemType);
		assertThat(itemTypeService.addItemType(itemType)).isEqualTo(itemType);
	}
	
	@Test
	void getAllItemTypes() {
		List<ItemType> types = java.util.Arrays.asList(
				new ItemType("Books", 0),
				new ItemType("CD", 10),
				new ItemType("Cosmetics", 12)
				);
		ItemTypes itemTypes = new ItemTypes(types);
		
		when(itemTypeDao.findAll()).thenReturn(types);
		
		assertThat(itemTypeService.getAllTypes()).isEqualTo(itemTypes);
	}
	
	
	
	@Test
	void getTaxRateRightItem() {
		
	ItemType itemType = new ItemType("CD",10.0f);
		
		when(itemTypeDao.getById("CD")).thenReturn(itemType);
		assertThat(itemTypeService.getTaxtRate("CD")).isEqualTo(itemType.getTaxRate());
		
	}
	// fix this
	@Test
	void getTaxRateWrongItem() {
		
		Float f=Float.NaN;
		
		when(itemTypeDao.getById("Pen")).thenReturn(null);
		assertThat(itemTypeService.getTaxtRate("Pen")).isEqualTo(f);
		
	}
	@Test
	void setTaxRateRightItem() {
		
	ItemType itemType = new ItemType("CD",11.0f);
		
		when(itemTypeDao.getById("CD")).thenReturn(itemType);
		assertThat(itemTypeService.setTaxtRate("CD",11.0f).getTaxRate()).isEqualTo(itemType.getTaxRate());
		
	}
	@Test
	void setTaxRateWrongItem() {
		
	ItemType itemType = new ItemType("Pen",11.0f);
		
		when(itemTypeDao.getById("Pen")).thenReturn(itemType);
		assertThat(itemTypeService.getTaxtRate("Pen")).isEqualTo(itemType.getTaxRate());
		
	}
	
}
