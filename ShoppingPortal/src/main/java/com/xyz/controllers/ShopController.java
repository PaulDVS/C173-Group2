package com.xyz.controllers;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xyz.entities.Item;
import com.xyz.entities.ItemType;
import com.xyz.entities.ItemTypes;
import com.xyz.entities.Items;
import com.xyz.entities.User;
import com.xyz.exception.UserCreationError;
import com.xyz.service.AccountService;
import com.xyz.service.StockTypeService;

@Controller
public class ShopController {
	@Autowired
	AccountService accountService;
	
	@Autowired
	private StockTypeService stockTypeServiceImp;
	
	//Homepage, takes the user to the Login/Register webpage.
	@RequestMapping("/")
	public ModelAndView getMainPageController() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", "Hello and welcome to our site.");
		modelAndView.setViewName("Login");
		return modelAndView;
	}
	
	//Attempt to login using a username and password
		//On fail go back to Login page with an appropriate message
		//On success go to Store Selection page and load user into session
	@RequestMapping("/Login")
	public ModelAndView attemptLogin(@ModelAttribute("userName") String userName, @ModelAttribute("password") String password, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		User user = accountService.loginCheck(userName, password);
		if(user!=null) {
			
			ItemTypes itemTypes = stockTypeServiceImp.getAllItemTypes();
			modelAndView.addObject("types", itemTypes.getItemTypes());
			modelAndView.setViewName("StoreSelection");
			
			session.setAttribute("currentUser", user);	
		} else {
			modelAndView.addObject("message", "Login Failed. Please try again.");
			modelAndView.setViewName("Login");
		}

		return modelAndView;
	}
	
	//Attempt to register a new user using input details
		//If the code doesn't throw an exception go to Store Selection page and load user into session
		//If an error is thrown go back to Login page and display the error message.
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
	
	@RequestMapping(value="/Register", method=RequestMethod.POST)
	public ModelAndView createAccount(@ModelAttribute("userEmail") String userEmail, @ModelAttribute("userPhone") String userPhone, @ModelAttribute("userAdd") String userAdd, @ModelAttribute("userName") String userName, @ModelAttribute("password") String password, HttpSession session, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		User newUser = new User(userEmail, userPhone, userAdd, userName, password);
		
		try {
			User checkedUser = accountService.registerCheck(newUser);
			modelAndView.setViewName("StoreSelection");
			session.setAttribute("currentUser", checkedUser);	
			
			response.sendRedirect("/");
			
		}  catch (UserCreationError e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("Login");
		} catch (Exception e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("Login");
		}

		
		return modelAndView;
	}
	
	@RequestMapping("/ViewShop")
	public ModelAndView viewShopTest() {
		ModelAndView modelAndView = new ModelAndView();
		ItemType itemType1 = new ItemType("Books", 0);
		Item item01 = new Item(1001, "Book 1", itemType1, 10, 3);
		Item item02 = new Item(1002, "Book 2", itemType1, 15, 1000);
		Item item03 = new Item(1003, "Book 3", itemType1, 20, 1000);

		
		List<Item> listitem = new ArrayList();
		listitem.add(item01);
		listitem.add(item02);
		listitem.add(item03);
		
		
		modelAndView.addObject("items", listitem);
		modelAndView.setViewName("ShopPage");
		return modelAndView;
	}
	
	@RequestMapping("/ViewItem")
	public ModelAndView viewItemPage(@ModelAttribute Item item) {
		ModelAndView modelAndView = new ModelAndView();
		
//		//temporary, TODO fix implementation
//		ItemType tempType = new ItemType("book", 12.5f);
//		Item item = new Item(10, "item", tempType, 200, 23);
//		//
		
		modelAndView.addObject("item", item);
		modelAndView.setViewName("ItemPage");
		
		return modelAndView;
	}
	
	@RequestMapping("/itemView")
	public void viewShopTest2(@ModelAttribute("item") Items currentItem) {
		System.out.println(currentItem);
	}
	
}
