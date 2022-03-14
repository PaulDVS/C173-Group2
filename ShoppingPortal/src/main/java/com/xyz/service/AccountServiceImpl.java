package com.xyz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.xyz.entities.User;
import com.xyz.exception.UserCreationError;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private RestTemplate restTemplate;

	//Login service calls the Login check function of the Account API
		//On successfull Login, returns the user object
	@Override
	public User loginCheck(String userName, String password) {
		return restTemplate.getForObject("http://localhost:8083/login/" + userName + "/" + password, User.class);
	}

	//Register user service calls the Register function of the Account API
		//If the input data doesn't conform to standard, throw error with appropriate message
		//If username or email already exist, throw error with appropriate message. Uses the Check user function of the Account API
		//If data is valid, Register user and return new user object
		//Final Check to make sure the user object was returned successfully
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
		
		if(checkUser == null) {
			throw new UserCreationError("Error Registration failed");
		}
		return checkUser;
	}

}
