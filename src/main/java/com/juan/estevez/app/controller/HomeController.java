package com.juan.estevez.app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
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
import com.juan.estevez.app.model.User;
import com.juan.estevez.app.service.IOrderDetailService;
import com.juan.estevez.app.service.IOrderService;
import com.juan.estevez.app.service.IUserService;
import com.juan.estevez.app.service.ProductService;

@Controller
@RequestMapping("/")
public class HomeController {

	List<OrderDetail> details = new ArrayList<>();
	Order order = new Order();

	@Autowired
	private ProductService productService;
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IOrderDetailService orderDetailService;
	
	@Autowired 
	private IUserService userService;

	@GetMapping("")
	public String Home(Model model, HttpSession session) {
		model.addAttribute("products", productService.findAll());
		model.addAttribute("session", session.getAttribute("iduser"));
		return "user/home";
	}

	@GetMapping("productHome/{id}")
	public String productHome(@PathVariable Integer id, Model model) {
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
		
		/**
		 * Validar que el producto no se añada mas de una vez.
		 */
		Integer idProduct = product.getId();
		boolean joined = details.stream().anyMatch(p -> p.getProduct().getId() == idProduct);
		
		if (!joined) {
			details.add(orderDetail);
		}
		
		totalSum = details.stream().mapToDouble(dt -> dt.getTotal()).sum();
		
		order.setTotal(totalSum);
		model.addAttribute("cart", details);
		model.addAttribute("order", order);
		
		return "user/shoppingcart";
	}

	@GetMapping("/delete/cart/{id}")
	public String deleteProductCart(@PathVariable Integer id, Model model) {
		List<OrderDetail> newOrders = new ArrayList<>();
		
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
	
	@GetMapping("/getCart")
	public String getCart(Model model, HttpSession session) {
		model.addAttribute("cart", details);
		model.addAttribute("order", order);
		model.addAttribute("session", session.getAttribute("iduser"));
		return "user/shoppingcart";
	}
	
	@GetMapping("/order")
	public String order(Model model, HttpSession session) {
		User user = userService.findById(Integer.parseInt(session.getAttribute("iduser").toString())).get();
		
		model.addAttribute("cart", details);
		model.addAttribute("order", order);
		model.addAttribute("user", user);
		
		return "user/summaryorder";
	}
	
	@GetMapping("/saveOrder")
	public String saveOrder(HttpSession session) {
		Date creationDate = new Date();
		order.setCreationDate(creationDate);
		order.setNumber(orderService.generateOrderNumber(10000));
		 
		User user = (User) userService.findById(Integer.parseInt(session.getAttribute("iduser").toString())).get();
		order.setUser(user);
		orderService.save(order);
		
		//guardando detalles
		for(OrderDetail dt: details) {
			dt.setOrder(order);
			orderDetailService.save(dt);
		}
		
		//limpiar detalle lista y orden
		order = new Order();
		details.clear();
		
		return "redirect:/";
	}
	
	@PostMapping("/search")
	public String searchProduct(@RequestParam String name, Model model) {
		List<Product> products = productService.findAll().stream().filter(p -> p.getName().contains(name.toLowerCase() )).collect(Collectors.toList());
		model.addAttribute("products", products);
		return "user/home";
	}
}
