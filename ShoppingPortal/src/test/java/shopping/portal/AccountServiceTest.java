package shopping.portal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.xyz.entities.User;
import com.xyz.service.AccountService;
import com.xyz.service.AccountServiceImpl;
import com.xyz.shopping.portal.ShoppingPortalApplication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ShoppingPortalApplication.class)
@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

	@Mock
	RestTemplate restTemplate;
	
	@InjectMocks
	AccountService accountService = new AccountServiceImpl();
	
	
//loginCheck() Testing
	
	//Testing valid user
	@Test
	void t001() {
		User user = new User();
		
		when(restTemplate.getForObject("http://localhost:8083/login/paul/lychee", User.class)).thenReturn(user);
		
		assertThat(accountService.loginCheck("paul", "lychee")).isEqualTo(user);
	}
	
	//Testing null user
	@Test
	void t002() {
		User user = null;
		
		when(restTemplate.getForObject("http://localhost:8083/login/doug/abc", User.class)).thenReturn(user);
		
		assertThat(accountService.loginCheck("doug", "abc")).isEqualTo(null);
	}
	
//registerCheck() Testing
	
	// Testing Unique and valid user
	@Test
	void t003() {
			User user = new User("Barry@gmail.com", "0800 000 0015", "West Drive", "Barry", "passWORD");
			
			when(restTemplate.getForObject("http://localhost:8083/checkUser/" + user.getCustomerEmail() + "/" + user.getCustomerName(), String.class))
					.thenReturn(null);
			
			when(restTemplate.postForObject("http://localhost:8083/register", user, User.class))
				.thenReturn(user);
			
			assertThat(accountService.registerCheck(user)).isEqualTo(user);
	}
		
	//Testing non-unique user
	@Test
	void t004() {
		User user = new User("Barry@gmail.com", "0800 000 0015", "West Drive", "Barry", "passWORD");
		User resultUser = null;
		
		when(restTemplate.getForObject("http://localhost:8083/checkUser/" + user.getCustomerEmail() + "/" + user.getCustomerName(), String.class))
				.thenReturn(null);
		
		when(restTemplate.postForObject("http://localhost:8083/register", user, User.class))
			.thenReturn(resultUser);
		
		assertThat(accountService.registerCheck(user)).isEqualTo(resultUser);
	}
}
