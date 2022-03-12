package com.xyz.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.entities.User;
import com.xyz.service.AccountService;

@RestController
public class AccountResource {
	@Autowired
	AccountService accountServce;
	
	//Login api using a password and username
	@GetMapping(path = "/login/{userName}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
	public User login(@PathVariable String userName, @PathVariable String password) {
		return accountServce.login(userName, password);
	}
	
	//Api to check if username or email are already in use.
	@GetMapping(path = "/checkUser/{userEmail}/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String checkUser(@PathVariable String userEmail, @PathVariable String userName) {
		String returnString = accountServce.checkUser(userEmail, userName);
		return returnString;
	}
	
	//APi to register a new user
	@PostMapping(path = "/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public User register(@RequestBody User user) {
		return accountServce.register(user);
	}
}
