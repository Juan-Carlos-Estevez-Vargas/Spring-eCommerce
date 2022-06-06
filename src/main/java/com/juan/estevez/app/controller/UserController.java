package com.juan.estevez.app.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.juan.estevez.app.model.Order;
import com.juan.estevez.app.model.User;
import com.juan.estevez.app.service.IOrderService;
import com.juan.estevez.app.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IOrderService orderService;
	
	@GetMapping("/registration")
	public String create() {
		return "user/registro";
	}
	
	@PostMapping("/save")
	public String save(User user) {
		user.setType("USER");
		userService.save(user);
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@PostMapping("/acceder")
	public String acceder(User user, HttpSession session) {
		Optional<User> usuario = userService.findByEmail(user.getEmail());
		
		if(usuario.isPresent()) {
			session.setAttribute("idusuario", usuario.get().getId());
			if(usuario.get().getType().equals("ADMIN")) {
				return "redirect:/administrator";
			}else {
				return "redirect:/";
			}
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/compras")
	public String obtenerCompras(HttpSession session, Model model) {
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		User user = userService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		List<Order> orders = orderService.findByUser(user);
		model.addAttribute("ordenes", orders);
		return "user/compras";
	}
	
	@GetMapping("/detalle/{id}")
	public String detalleCompra(@PathVariable Integer id, HttpSession session, Model model) {
		Optional<Order> order = orderService.findById(id);
		model.addAttribute("detalles", order.get().getDetail());
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		return "user/dettallecompra";
	}
}
