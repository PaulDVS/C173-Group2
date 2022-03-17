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
	public String getMainPageController() {

		return "redirect:/Account/Login";
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
