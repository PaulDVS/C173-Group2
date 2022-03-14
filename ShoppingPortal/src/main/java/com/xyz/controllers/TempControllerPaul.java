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
import com.xyz.service.OrderService;

@Controller
public class TempControllerPaul {
	@Autowired
	OrderService orderService;

	
	//Loads in the user basket of items using the email of the current user in session.
	@RequestMapping("/ViewCart")
	public ModelAndView viewShopTest(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		List<BasketItemFull> basketItemsFull = new ArrayList();
		
		User user = (User) session.getAttribute("currentUser");
		System.out.println(user);
		
		basketItemsFull = orderService.showCart(user.getCustomerEmail());
		
		System.out.println(basketItemsFull);
		modelAndView.addObject("basketItemsFull", basketItemsFull);
		modelAndView.setViewName("Cart");
		return modelAndView;
	}
	
	
}
