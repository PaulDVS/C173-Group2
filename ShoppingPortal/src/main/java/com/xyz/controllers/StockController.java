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

import com.xyz.entities.Item;
import com.xyz.entities.ItemType;
import com.xyz.entities.Items;
import com.xyz.entities.StockItem;
import com.xyz.service.StockService;
import com.xyz.service.StockServiceImpl;


@Controller
public class StockController {
	
	@Autowired
	StockService stockServiceImpl;
	
	@RequestMapping(value="/getStockTypes", method=RequestMethod.GET)
	public ModelAndView getStockTypesController() {
		ModelAndView modelAndView = new ModelAndView();
		//ItemTypes types = stockServiceImpl.get();   //TODO
		//modelAndView.addObject("types", types);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/getStockOfType", method=RequestMethod.GET)
	public ModelAndView getStockOfType(@RequestParam("stockType") ItemType type) {
		ModelAndView modelAndView = new ModelAndView();
		Items items = stockServiceImpl.getItemsByType(type);
	    modelAndView.addObject("items", items);
		return modelAndView;
	}
	
	@RequestMapping(value="/getStockQuantityById/{id}", method=RequestMethod.GET)
	public ModelAndView getStockQuantityById(@PathVariable("id") int id) {
		ModelAndView modelAndView = new ModelAndView();
		int quantity = stockServiceImpl.getStockQuantityById(id);
		modelAndView.addObject("id", id);
		modelAndView.addObject("quantity", quantity);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/setStockQuantityById", method=RequestMethod.PUT)
	public ModelAndView setStockQuantityById(@RequestBody StockItem item) {
		ModelAndView modelAndView = new ModelAndView();
		stockServiceImpl.setStockQuantityById(item);
		modelAndView.addObject("item", item);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/getStockPriceById/{id}", method=RequestMethod.GET)
	public ModelAndView getStockPriceById(@PathVariable("id") int id) {
		ModelAndView modelAndView = new ModelAndView();
		float price = stockServiceImpl.getPriceById(id);
		modelAndView.addObject("id", id);
		modelAndView.addObject("price", price);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/getStockTaxByType", method=RequestMethod.GET)
	public ModelAndView getStockTaxByType(@RequestParam("stockType") String type) {
		ModelAndView modelAndView = new ModelAndView();
		//float taxRate = stockServiceImpl.get(type);
		//modelAndView.addObject("taxRate", taxRate);    /// TODO
		return modelAndView;
	}
	
	@RequestMapping(value="/searchStockById/{id}", method=RequestMethod.GET)
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
