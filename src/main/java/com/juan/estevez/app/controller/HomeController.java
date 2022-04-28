package com.juan.estevez.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.juan.estevez.app.model.Order;
import com.juan.estevez.app.model.OrderDetail;
import com.juan.estevez.app.model.Product;
import com.juan.estevez.app.service.ProductService;

@Controller
@RequestMapping("/")
public class HomeController {

	private final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	List<OrderDetail> details = new ArrayList<OrderDetail>();
	Order order = new Order();

	@Autowired
	private ProductService productService;

	@GetMapping("")
	public String Home(Model model) {
		model.addAttribute("products", productService.findAll());
		return "user/home";
	}

	@GetMapping("productHome/{id}")
	public String productHome(@PathVariable Integer id, Model model) {
		LOGGER.info("Id del producto como parámetro {}", id);
		Product product = new Product();
		Optional<Product> productOptional = productService.get(id);
		product = productOptional.get();
		model.addAttribute("product", product);
		return "user/productHome";
	}

	@PostMapping("/cart")
	public String addCart(@RequestParam Integer id, @RequestParam Integer cant, Model model) {
		OrderDetail orderDetail = new OrderDetail();
		Product product = new Product();
		double totalSum = 0;
		Optional<Product> optionalProduct = productService.get(id);
		
		product = optionalProduct.get();
		
		orderDetail.setCant(cant);
		orderDetail.setPrice(product.getPrice());
		orderDetail.setTotal(product.getPrice() * cant);
		orderDetail.setName(product.getName());
		orderDetail.setProduct(product);
		
		details.add(orderDetail);
		totalSum = details.stream().mapToDouble(dt -> dt.getTotal()).sum();
		order.setTotal(totalSum);
		model.addAttribute("cart", details);
		model.addAttribute("order", order);
		
		return "user/carrito";
	}

	@GetMapping("/delete/cart/{id}")
	public String deleteProductoCart(@PathVariable Integer id, Model model) {
		List<OrderDetail> newOrders = new ArrayList<OrderDetail>();
		
		for (OrderDetail orderDetail : details) {
			if (orderDetail.getProduct().getId() != id) {
				newOrders.add(orderDetail);
			}
		}
		
		details = newOrders;
		
		double totalSum = 0;
		totalSum = details.stream().mapToDouble(dt -> dt.getTotal()).sum();
		order.setTotal(totalSum);
		model.addAttribute("cart", details);
		model.addAttribute("order", order);
		
		return "user/carrito";
	}
}
