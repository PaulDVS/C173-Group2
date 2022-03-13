package com.xyz.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.xyz.service.EmailService;
import com.xyz.model.EmailRequest;

@RestController 

public class EmailContrller {
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping("/welcome")
	
	public String welcome ()
	{
		return "Hello, this is email API";
	}
	
	@RequestMapping(value ="/sendmail", method =RequestMethod.POST) 
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request) {

		System.out.println(request);
		this.emailService.sendEmail(request.getSubject(), request.getMessage(), request.getTo());
		return ResponseEntity.ok("Done");
	}
	

}
