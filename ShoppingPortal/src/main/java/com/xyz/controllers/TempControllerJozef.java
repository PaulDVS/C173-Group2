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

public class TempControllerJozef {
	@Autowired
	OrderService orderService;
	
	public ModelAndView addCartItem(@ModelAttribute("itemId") int itemId, @ModelAttribute("inputQuantity") int quantity, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		User user = (User)session.getAttribute("currentUser");
		String responseString = orderService.addItem(); // TODO implement the addItem method there
		
		List<BasketItemFull> basketItemFull = new ArrayList<>();
		basketItemFull = orderService.showCart(user.getCustomerEmail());
		modelAndView.addObject("basketItemsFull", basketItemFull);
		modelAndView.setViewName("Cart");
		return modelAndView;
		// TODO requires resting
	}
}