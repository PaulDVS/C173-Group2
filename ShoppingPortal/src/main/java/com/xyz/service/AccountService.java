package com.xyz.service;

import com.xyz.entities.User;

public interface AccountService {
	public User loginCheck(String userName, String password);
	public User registerCheck(User user) ;
}
