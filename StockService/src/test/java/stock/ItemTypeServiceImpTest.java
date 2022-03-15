package stock;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.xyz.stock.entity.ItemType;
import com.xyz.stock.persistence.ItemTypeDao;
import com.xyz.stock.service.ItemTypeService;
import com.xyz.stock.service.ItemTypeServiceImp;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ItemTypeServiceImpTest {
	
	@Mock
	private ItemTypeDao itemTypeDao;

	@InjectMocks
	private ItemTypeService itemTypeService = new ItemTypeServiceImp();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetAllTypes() {
		
		ItemType itemType = new ItemType("CD",0.0f);
		
		when(itemTypeDao.getById("CD")).thenReturn(itemType);
		assertThat(itemTypeService.getTaxtRate("CD")).isEqualTo(itemType);
	}

	@Test
	void testAddItemType() {
		fail("Not yet implemented");
	}

	@Test
	void testSetTaxtRate() {
		fail("Not yet implemented");
	}

	@Test
	void testGetTaxtRate() {
		fail("Not yet implemented");
	}

}
