package com.xyz.service;

import com.xyz.entities.User;


public interface AccountService {
	
	User login(String userName, String password);
	User register(User user);
	String checkUser(String userEmail, String userName);
}
