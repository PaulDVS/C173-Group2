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
		return userDoa.save(user);
	}
}
