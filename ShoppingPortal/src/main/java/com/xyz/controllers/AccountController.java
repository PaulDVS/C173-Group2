package com.xyz.controllers;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.xyz.entities.Login;
import com.xyz.entities.User;
import com.xyz.service.AccountService;


@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
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
	public String login(@Valid Login login,BindingResult bindingResult,HttpSession session) {
		
		session.removeAttribute("message");
		if (bindingResult.hasErrors()) {
			
			return "Login";
		}else {
			
			User user = accountService.loginCheck(login.getUserName(), login.getPassword());
			
			if(user!=null) {
				
				session.setAttribute("currentUser", user);	
				session.removeAttribute("message");
				
			return "redirect:/StockItemsType/All";
			
			} else {
				String message = "Failed to login. Either user name is wrong or the password.";
				session.setAttribute("message", message);
				
				return "Login";
			}
		}
		
		
	
		

		
	}
	
}
