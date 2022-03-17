package account;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.xyz.account.AccountServiceApplication;
import com.xyz.entities.User;
import com.xyz.persistence.UserDao;
import com.xyz.service.AccountService;
import com.xyz.service.AccountServiceImpl;

@SpringBootTest(classes = AccountServiceApplication.class)
@ExtendWith(MockitoExtension.class)
class AccountServiceApplicationTests {

	@Mock
	UserDao userDao;
	
	@InjectMocks
	AccountService accountService = new AccountServiceImpl();
	
	
	//CheckUser Testing
	
	//User does not Exist
	@Test
	void t001() {
		User user1 = null;
		User user2 = null;
		
		String userEmail= "Barry@gmail.com";
		String userName = "Barry";
		
		when(userDao.findUserByEmail(userEmail)).thenReturn(user1);
		when(userDao.findUserByUserName(userName)).thenReturn(user2);
		
		assertThat(accountService.checkUser(userEmail, userName)).isEqualTo("");
	}
	
	//User does not Exist
	@Test
	void t002() {
		User user1 = null;
		User user2 = null;
			
		String userEmail= "Doug@gmail.com";
		String userName = "Doug";
			
		when(userDao.findUserByEmail(userEmail)).thenReturn(user1);
		when(userDao.findUserByUserName(userName)).thenReturn(user2);
			
		assertThat(accountService.checkUser(userEmail, userName)).isEqualTo("");
	}
	
	//User does Exist
	@Test
	void t003() {
		User user1 = null;
		User user2 = new User("Paul@Wiley.com", "0800 000 0004", "19 East Lane", "Pau;", "lychee");
			
		String userEmail= "Test@gmail.com";
		String userName = "Paul";
			
		when(userDao.findUserByEmail(userEmail)).thenReturn(user1);
		when(userDao.findUserByUserName(userName)).thenReturn(user2);
			
		assertThat(accountService.checkUser(userEmail, userName)).isEqualTo("Username is already in use");
	}
	
	//User does Exist
		@Test
		void t004() {
			User user1 = new User("Paul@Wiley.com", "0800 000 0004", "19 East Lane", "Pau;", "lychee");
			User user2 = null;
				
			String userEmail= "Paul@Wiley.com";
			String userName = "Test";
				
			when(userDao.findUserByEmail(userEmail)).thenReturn(user1);
			when(userDao.findUserByUserName(userName)).thenReturn(user2);
				
			assertThat(accountService.checkUser(userEmail, userName)).isEqualTo("Email is already in use");
		}

}
