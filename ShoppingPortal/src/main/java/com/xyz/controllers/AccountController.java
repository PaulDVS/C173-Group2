package com.xyz.controllers;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	public String register(@ModelAttribute("user") User user) {
		
		
			try {
				accountService.registerCheck(user);
				return "redirect:Login";
			} catch(Exception e) {
				e.getStackTrace();
			}
		
			return "redirect:Error";
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
	public String login(@ModelAttribute("login") Login login, HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView();
		User user = accountService.loginCheck(login.getUserName(), login.getPassword());
	
		if(user!=null) {
			
			session.setAttribute("currentUser", user);	
		return "redirect:/StockItemsType/All";
		
		} else {
			modelAndView.addObject("message", "Login Failed. Please try again.");
			modelAndView.setViewName("Login");
			return "redirect:Login";
		}

		
	}
}
