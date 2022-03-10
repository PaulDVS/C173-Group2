package com.xyz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.xyz.entities.User;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public User loginCheck(String userName, String password) {
		return restTemplate.getForObject("http://localhost:8083/login/" + userName + "/" + password, User.class);
	}

}
