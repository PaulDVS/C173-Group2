package com.xyz.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xyz.entities.Item;
import com.xyz.entities.ItemType;
import com.xyz.entities.ItemTypes;
import com.xyz.entities.Items;
import com.xyz.entities.StockItem;
import com.xyz.service.StockService;
import com.xyz.service.StockServiceImpl;
import com.xyz.service.StockTypeService;


/**
 * 
 * @name StockController.java 
 * @version 1.0
 * @author Jozef, Mohsen
 * @create  14/03/2022
 * @update date 15/03/2022
 *  
 */
@Controller
public class StockController {
	
	@Autowired
	StockService stockServiceImpl;
	
	@Autowired
	StockTypeService stockTypeServiceImp;
	
	/**
	 * Returns a list of item types to the Store Selection view
	 * Example: http://localhost:8085/StockItemsType/All
	 * @param void
	 * @return a list of ItemType class to the StoreSelection view 
	 */
	
	@RequestMapping(value="/StockItemsType/All", method=RequestMethod.GET)
	public ModelAndView getStockTypesController() {
		
		ModelAndView modelAndView = new ModelAndView();
		ItemTypes itemTypes = stockTypeServiceImp.getAllItemTypes();
		modelAndView.addObject("types", itemTypes.getItemTypes());
		modelAndView.setViewName("StoreSelection");
		return modelAndView;
	}
	
	/* 
	@RequestMapping(value="/StockItems/All", method=RequestMethod.GET)
	public ModelAndView getAllStockItems() {
		ModelAndView modelAndView = new ModelAndView();
		Items items = stockServiceImpl.getAllItems();
	    modelAndView.setViewName("Items");
		modelAndView.addObject("items", items.getItems());
		return modelAndView;
	}
	*/
	/**
	 * Returns items by the given type for example given books as a type, 
	 * returns all the books in the stock
	 * Example: http://localhost:8085/StockItems/ByType/?ItemType=Books
	 * @param takes the items type
	 * @return a list of items with the same type
	 */
	
	@RequestMapping(value="/StockItems/ByType", method=RequestMethod.GET)
	public ModelAndView getItemsByType(@ModelAttribute("ItemType") String itemType) {
		
		ModelAndView modelAndView = new ModelAndView();
		ItemType type = new ItemType();
		type.setType(itemType);
		Items items = stockServiceImpl.getItemsByType(type);
	    modelAndView.setViewName("ShopPage");
		modelAndView.addObject("items", items.getItems());
		return modelAndView;
	}
	/* 
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
	*/
	/* 
	@RequestMapping(value="/Items/setStockQuantityById", method=RequestMethod.PUT)
	public ModelAndView setStockQuantityById(@RequestBody StockItem item) {
		ModelAndView modelAndView = new ModelAndView();
		stockServiceImpl.setStockQuantityById(item);
		modelAndView.addObject("item", item);
		
		return modelAndView;
	}
	*/
	/* 
	@RequestMapping(value="/Items/getStockPriceById/{id}", method=RequestMethod.GET)
	public ModelAndView getStockPriceById(@PathVariable("id") int id) {
		
		ModelAndView modelAndView = new ModelAndView();
		float price = stockServiceImpl.getPriceById(id);
		modelAndView.addObject("id", id);
		modelAndView.addObject("price", price);
		return modelAndView;
	}
	*/
	
	/*
	@RequestMapping(value="/Items/getStockTaxByType", method=RequestMethod.GET)
	public ModelAndView getStockTaxByType(@RequestParam("stockType") String type) {
		ModelAndView modelAndView = new ModelAndView();
		//float taxRate = stockServiceImpl.get(type);
		//modelAndView.addObject("taxRate", taxRate);   
		return modelAndView;
	}
	*/
	/* 
	@RequestMapping(value="/StockItems/FindItemById/{id}", method=RequestMethod.GET)
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
	*/
}
