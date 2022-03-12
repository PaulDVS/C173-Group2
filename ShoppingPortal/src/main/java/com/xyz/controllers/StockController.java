package com.xyz.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xyz.service.StockService;
import com.xyz.service.StockServiceImpl;


@Controller
public class StockController {
	
	@Autowired
	StockService stockServiceImpl;
	
	// handles get request for a list of all types of stock
	@RequestMapping(value="/getStockTypes", method=RequestMethod.GET)
	public ModelAndView getStockTypesController() {
		ModelAndView modelAndView = new ModelAndView();
		//ItemTypes types = itemTypeService.getAllTypes();
		//modelAndView.addObject("types", types);
		
		return modelAndView;
	}
	
	// handles get request for getting a list of stock of a certain type
	@RequestMapping(value="/getStockOfType", method=RequestMethod.GET)
	public ModelAndView getStockOfType() {//@RequestParam("stockType") ItemType type) {
		ModelAndView modelAndView = new ModelAndView();
		//Items items = itemService.getItemsByType(type);
		//modelAndView.addObject("items", items);
		return modelAndView;
	}
	
	// handles get request for getting the quantity of an item
	@RequestMapping(value="/getStockQuantityById/{id}", method=RequestMethod.GET)
	public ModelAndView getStockQuantityById(@PathVariable("id") int id) {
		ModelAndView modelAndView = new ModelAndView();
		//int quantity = itemService.getStockQuantityById(id);
		modelAndView.addObject("id", id);
		//modelAndView.addObject("quantity", quantity);
		
		return modelAndView;
	}
	
	// handles post request for setting the quantity of an item in stock
	@RequestMapping(value="/setStockQuantityById/{id}/{quantity}", method=RequestMethod.POST)
	public ModelAndView setStockQuantityById(@PathVariable("id") int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("id", id);
		//itemService
		
		//todo
		
		return modelAndView;
	}
	
	// handles get request for getting the price of an item
	@RequestMapping(value="/getStockPriceById/{id}", method=RequestMethod.GET)
	public ModelAndView getStockPriceById(@PathVariable("id") int id) {
		ModelAndView modelAndView = new ModelAndView();
		//float price = itemService.getPriceById(id);
		modelAndView.addObject("id", id);
		//modelAndView.addObject("price", price);
		
		return modelAndView;
	}
	
	// handles get request for getting the tax rate for an item of a certain type
	@RequestMapping(value="/getStockTaxByType", method=RequestMethod.GET)
	public ModelAndView getStockTaxByType(@RequestParam("stockType") String type) {
		ModelAndView modelAndView = new ModelAndView();
		//float taxRate = itemTypeService.getTaxtRate(type);
		//modelAndView.addObject("taxRate", taxRate);
		return modelAndView;
	}
	
	// handles get request for getting an item by its ID
	@RequestMapping(value="/searchStockById/{id}", method=RequestMethod.GET)
	public ModelAndView searchStockController(@PathVariable("id") int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("id", id);
		//Item item = itemService.getItemById(id);
		if(true){//item == null) {
			modelAndView.addObject("exists", false);
		} else {
			modelAndView.addObject("exists", true);
			//modelAndView.addObject("item", item);
		}
		
		return modelAndView;
	}
}
