package com.juan.estevez.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.juan.estevez.app.model.Product;
import com.juan.estevez.app.service.IOrderService;
import com.juan.estevez.app.service.IUserService;
import com.juan.estevez.app.service.ProductService;

/**
 * Controlador de tipo Administrador.
 * 
 * @author Juan Carlos Estevez Vargas.
 *
 */
@Controller
@RequestMapping("/administrator")
public class AdministratorController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IOrderService orderService;

	/***
	 * Muestra la página principal del aplicativo.
	 * 
	 * @return vista home.html.
	 */
	@GetMapping("")
	public String home(Model model) {
		List<Product> products = productService.findAll();
		model.addAttribute("products", products);
		return "administrator/home";
	}
	
	@GetMapping("/usuarios")
	public String usuarios(Model model) {
		model.addAttribute("usuarios", userService.findAll());
		return "administrator/usuarios";
	}

	@GetMapping("/ordenes")
	public String ordenes(Model model) {
		model.addAttribute("ordenes", orderService.findAll());
		return "administrador/ordenes";
	}
}
