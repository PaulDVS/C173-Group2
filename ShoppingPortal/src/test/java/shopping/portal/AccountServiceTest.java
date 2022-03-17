package shopping.portal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.xyz.service.AccountService;
import com.xyz.service.AccountServiceImpl;
import com.xyz.shopping.portal.ShoppingPortalApplication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ShoppingPortalApplication.class)
@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

	@Mock
	RestTemplate restTemplate;
	
	@InjectMocks
	AccountService accountService = new AccountServiceImpl();
}
