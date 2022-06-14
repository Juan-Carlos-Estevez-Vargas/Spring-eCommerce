package com.juan.estevez.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.juan.estevez.app.model.Order;
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
	
	@GetMapping("/users")
	public String users(Model model) {
		model.addAttribute("users", userService.findAll());
		return "administrator/users";
	}

	@GetMapping("/orders")
	public String orders(Model model) {
		model.addAttribute("orders", orderService.findAll());
		return "administrador/orders";
	}
	
	@GetMapping("/detail{id}")
	public String detail(Model model, @PathVariable Integer id) {
		Order order = orderService.findById(id).get();
		model.addAttribute("details", order.getDetail());
		return "/administrator/detailorder";
	}
}
