package com.xyz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.xyz.entities.User;
import com.xyz.exception.UserCreationError;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private RestTemplate restTemplate;

	// attempts to log in a user
	@Override
	public User loginCheck(String userName, String password) {
		return restTemplate.getForObject("http://localhost:8083/login/" + userName + "/" + password, User.class);
	}

	// attempts to register a new user account and returns the user
	// throws a UserCreationError if there is an issue with the creation details
	@Override
	public User registerCheck(User user) throws UserCreationError {
		User checkUser = null;
		
		if(user.getCustomerEmail().equals("") || user.getCustomerPhone().equals("") ||  user.getCustomerAddress().equals("") || (user.getCustomerName().equals("")) || user.getCustomerPassword().equals("")){
			throw new UserCreationError("Fields may not be blank");
			
		} else {
			String message = restTemplate.getForObject("http://localhost:8083/checkUser/" + user.getCustomerEmail() + "/" + user.getCustomerName(), String.class);
			if(message != null) {
				throw new UserCreationError(message);
			} else {
				checkUser = restTemplate.postForObject("http://localhost:8083/register", user, User.class);
			}
		}
		return checkUser;
	}

}
