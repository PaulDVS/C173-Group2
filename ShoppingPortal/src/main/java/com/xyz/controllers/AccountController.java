package com.xyz.controllers;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xyz.entities.ItemTypes;
import com.xyz.entities.Login;
import com.xyz.entities.User;
import com.xyz.exception.UserCreationError;
import com.xyz.service.AccountService;
import com.xyz.service.StockTypeService;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private StockTypeService stockTypeServiceImp;
	
	@RequestMapping(value="/Account/Register", method=RequestMethod.GET)
	public String register(Model model) {
		
		User user = new User();
		
		model.addAttribute("user", user);
		
		return "Register";
	}
	@RequestMapping(value="/Account/Register", method=RequestMethod.POST)
	public String register(@Valid User user,BindingResult bindingResult) {
		
		
		if (bindingResult.hasErrors()) {
			
			return "Register";
		}else {
			
			accountService.registerCheck(user);
			return "redirect:Login";
		}
	}
	
	@RequestMapping(value="/Account/Login", method=RequestMethod.GET)
	public String login(Model model) {
		
		Login login = new Login();
		
		model.addAttribute("login", login);
		
		return "Login";
	}
	
	
	//Attempt to login using a username and password
	//On fail go back to Login page with an appropriate message
	//On success go to Store Selection page and load user into session
	@RequestMapping(value = "/Account/Login", method=RequestMethod.POST)
	public String login(@Valid Login login,BindingResult bindingResult, HttpSession session) {
		
		if (bindingResult.hasErrors()) {
			
			return "Login";
		}else {
			
			User user = accountService.loginCheck(login.getUserName(), login.getPassword());
			
			if(user!=null) {
				
				session.setAttribute("currentUser", user);	
			return "redirect:/StockItemsType/All";
			
			} else {
				String message = "Failed to login. Either user name is wrong or the password.";
				bindingResult.addError(new ObjectError("message",message));
				
				return "Login";
			}
		}
		
		
	
		

		
	}
	
}
