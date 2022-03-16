package stock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.xyz.stock.StockServiceApplication;
import com.xyz.stock.entity.ItemType;
import com.xyz.stock.persistence.ItemTypeDao;
import com.xyz.stock.service.ItemTypeService;
import com.xyz.stock.service.ItemTypeServiceImp;


@SpringBootTest(classes=StockServiceApplication.class)
@ExtendWith(MockitoExtension.class)
class StockServiceApplicationTests {

	@Mock
	private ItemTypeDao itemTypeDao;

	@InjectMocks
	private ItemTypeService itemTypeService = new ItemTypeServiceImp();
	
	@Test
	void getTaxRate() {
		
	ItemType itemType = new ItemType("CD",10.0f);
		
		when(itemTypeDao.getById("CD")).thenReturn(itemType);
		assertThat(itemTypeService.getTaxtRate("CD")).isEqualTo(itemType.getTaxRate());
		
	}
	

}
