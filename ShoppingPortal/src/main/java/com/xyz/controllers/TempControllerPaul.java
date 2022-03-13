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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xyz.entities.BasketItemFull;
import com.xyz.entities.Item;
import com.xyz.entities.ItemType;
import com.xyz.entities.Items;
import com.xyz.entities.User;
import com.xyz.exception.UserCreationError;
import com.xyz.service.AccountService;

@Controller
public class TempControllerPaul {
	@Autowired
	AccountService accountService;

	
	@RequestMapping("/ViewCart")
	public ModelAndView viewShopTest(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		List<BasketItemFull> basketItemsFull = new ArrayList();
		BasketItemFull currentItem;
		
		int id;
		String name;
		int quantity;
		float price;
		float tax;
		float finalPrice;
		
		//#Get basket from session
		
		
		
		//Get local cart and sets up a BasketItemFull object for each basket item
//		for() { //#For each item in basket
//			currentItem = new BasketItemFull();
//			//#Add item data
//			
//			finalPrice = (quantity*price) * (tax/100);
//			currentItem = new BasketItemFull();
//			basketItemsFull.add(currentItem);
//		}
		
		
		
		// Temp set up
		BasketItemFull item1 = new BasketItemFull(1, "CD1", 10, 15, 10, 165);
		BasketItemFull item2 = new BasketItemFull(2, "Book1", 10, 8, 5, 84);
		basketItemsFull.add(item1);
		basketItemsFull.add(item2);
		//
		
		modelAndView.addObject("basketItemsFull", basketItemsFull);
		modelAndView.setViewName("Cart");
		return modelAndView;
	}
	
	
}
