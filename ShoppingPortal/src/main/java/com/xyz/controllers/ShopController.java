package com.xyz.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xyz.entities.User;
import com.xyz.exception.UserCreationError;
import com.xyz.service.AccountService;

@Controller
public class ShopController {
	@Autowired
	AccountService accountService;
	
	
	@RequestMapping("/")
	public ModelAndView getMainPageController() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", "Hello and welcome to our site.");
		modelAndView.setViewName("Login");
		return modelAndView;
	}
	
	@RequestMapping("/Login")
	public ModelAndView attemptLogin(@ModelAttribute("userName") String userName, @ModelAttribute("password") String password, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		User user = accountService.loginCheck(userName, password);
		if(user!=null) {
			modelAndView.setViewName("StoreSelection");
			session.setAttribute("currentUser", user);	
		} else {
			modelAndView.addObject("message", "Login Failed. Please try again.");
			modelAndView.setViewName("Login");
		}

		return modelAndView;
	}
	
	@RequestMapping("/Register")
	public ModelAndView createAccount(@ModelAttribute("userEmail") String userEmail, @ModelAttribute("userPhone") String userPhone, @ModelAttribute("userAdd") String userAdd, @ModelAttribute("userName") String userName, @ModelAttribute("password") String password, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		User newUser = new User(userEmail, userPhone, userAdd, userName, password);
		
		try {
			User checkedUser = accountService.registerCheck(newUser);
			modelAndView.setViewName("StoreSelection");
			session.setAttribute("currentUser", checkedUser);	
		}  catch (UserCreationError e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("Login");
		} catch (Exception e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("Login");
		}
		
		return modelAndView;
	}
	
}
