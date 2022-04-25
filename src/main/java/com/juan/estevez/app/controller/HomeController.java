package com.juan.estevez.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.juan.estevez.app.service.ProductService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("")
	public String Home(Model model) {
		model.addAttribute("products", productService.findAll());
		return "user/home";
	}
	
	@GetMapping("productHome/{id}")
	public String productHome(@PathVariable Integer id) {
		LOGGER.info("Id del producto como parámetro {}", id);
		return "user/productHome";
	}
	
}
