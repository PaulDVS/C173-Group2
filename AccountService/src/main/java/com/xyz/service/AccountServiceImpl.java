package com.xyz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.entities.User;
import com.xyz.persistence.UserDao;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	UserDao userDoa;

	@Override
	public User login(String userName, String password) {
		return userDoa.findUserByUserNameAndPassword(userName, password);
	}

	@Override
	public User register(User user) {
		System.out.println(user);
		return userDoa.save(user);
	}

	@Override
	public String checkUser(String userEmail, String userName) {
		String returnString = "";
		
		if((userDoa.findUserByEmail(userEmail) != null) && (userDoa.findUserByUserName(userName) != null)) {
			returnString = "Username and Email are both already in use";
		} else if(userDoa.findUserByEmail(userEmail) != null) {
			returnString = "Email is already in use";
		} else if(userDoa.findUserByUserName(userName) != null) {
			returnString = "Username is already in use";
		}
		
		return returnString;
	}
}
