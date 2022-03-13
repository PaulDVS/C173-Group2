package com.xyz.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xyz.entities.Item;
import com.xyz.entities.ItemType;
import com.xyz.entities.Items;
import com.xyz.entities.StockItem;
import com.xyz.service.StockService;



@Controller
public class StockController {
	
	@Autowired
	StockService stockServiceImpl;
	
	@RequestMapping(value="/Items/getStockTypes", method=RequestMethod.GET)
	public ModelAndView getStockTypesController() {
		ModelAndView modelAndView = new ModelAndView();
		//ItemTypes types = stockServiceImpl.get();   //TODO
		//modelAndView.addObject("types", types);
		
		return modelAndView;
	}
	@RequestMapping(value="/Items/All", method=RequestMethod.GET)
	public ModelAndView getAllStockItems() {
		ModelAndView modelAndView = new ModelAndView();
		Items items = stockServiceImpl.getAllItems();
	    modelAndView.setViewName("Items");
		modelAndView.addObject("items", items.getItems());
		return modelAndView;
	}
	
	
	@RequestMapping(value="/Items/getStockOfType", method=RequestMethod.GET)
	public ModelAndView getStockOfType(@RequestParam("stockType") ItemType type) {
		ModelAndView modelAndView = new ModelAndView();
		Items items = stockServiceImpl.getItemsByType(type);
	    modelAndView.setViewName("Items");
		modelAndView.addObject("items", items);
		return modelAndView;
	}
	
	@RequestMapping(value="/Items/getStockQuantityById/{id}", method=RequestMethod.GET)
	public ModelAndView getStockQuantityById(@PathVariable("id") int id) {
		System.out.println("We are in the ");
		ModelAndView modelAndView = new ModelAndView();
		int quantity = stockServiceImpl.getStockQuantityById(id);
		modelAndView.addObject("id", id);
		modelAndView.addObject("quantity", quantity);
		modelAndView.setViewName("Items");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/Items/setStockQuantityById", method=RequestMethod.PUT)
	public ModelAndView setStockQuantityById(@RequestBody StockItem item) {
		ModelAndView modelAndView = new ModelAndView();
		stockServiceImpl.setStockQuantityById(item);
		modelAndView.addObject("item", item);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/Items/getStockPriceById/{id}", method=RequestMethod.GET)
	public ModelAndView getStockPriceById(@PathVariable("id") int id) {
		ModelAndView modelAndView = new ModelAndView();
		float price = stockServiceImpl.getPriceById(id);
		modelAndView.addObject("id", id);
		modelAndView.addObject("price", price);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/Items/getStockTaxByType", method=RequestMethod.GET)
	public ModelAndView getStockTaxByType(@RequestParam("stockType") String type) {
		ModelAndView modelAndView = new ModelAndView();
		//float taxRate = stockServiceImpl.get(type);
		//modelAndView.addObject("taxRate", taxRate);    /// TODO
		return modelAndView;
	}
	
	@RequestMapping(value="/Items/searchStockById/{id}", method=RequestMethod.GET)
	public ModelAndView searchStockController(@PathVariable("id") int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("id", id);
		Item item = stockServiceImpl.getItemById(id);
		if(item == null) {
			modelAndView.addObject("exists", false);
		} else {
			modelAndView.addObject("exists", true);
			modelAndView.addObject("item", item);
		}
		
		return modelAndView;
	}
}
