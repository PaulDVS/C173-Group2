package com.xyz.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.entities.User;
import com.xyz.service.AccountService;

@RestController
public class AccountResource {
	@Autowired
	AccountService accountServce;
	
	@GetMapping(path = "/login/{userName}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
	public User login(@PathVariable String userName, @PathVariable String password) {
		return accountServce.login(userName, password);
	}
}
