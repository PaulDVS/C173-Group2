package shopping.portal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.xyz.service.OrderService;
import com.xyz.service.OrderServiceImpl;
import com.xyz.shopping.portal.ShoppingPortalApplication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ShoppingPortalApplication.class)
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

	@Mock
	RestTemplate restTemplate;
	
	@InjectMocks
	OrderService orderService = new OrderServiceImpl();
}
