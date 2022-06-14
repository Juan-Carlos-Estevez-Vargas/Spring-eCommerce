package com.juan.estevez.app.controller;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	BCryptPasswordEncoder passEncode = new BCryptPasswordEncoder();
	
	@GetMapping("/registration")
	public String create() {
		return "user/registry";
	}
	
	@PostMapping("/save")
	public String save(User user) {
		user.setType("USER");
		user.setPassword(passEncode.encode(user.getPassword())); 
		userService.save(user);
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@GetMapping("/access")
	public String access(User user, HttpSession session) {
		Optional<User> usuario = userService.findById(Integer.parseInt(session.getAttribute("iduser").toString()));
		
		if(usuario.isPresent()) {
			session.setAttribute("iduser", usuario.get().getId());
			if(usuario.get().getType().equals("ADMIN")) {
				return "redirect:/administrator";
			}else {
				return "redirect:/";
			}
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/shopping")
	public String getShopping(HttpSession session, Model model) {
		model.addAttribute("session", session.getAttribute("iduser"));
		User user = userService.findById(Integer.parseInt(session.getAttribute("iduser").toString())).get();
		List<Order> orders = orderService.findByUser(user);
		model.addAttribute("orders", orders);
		return "user/shopping";
	}
	
	@GetMapping("/detail/{id}")
	public String detailShopping(@PathVariable Integer id, HttpSession session, Model model) {
		Optional<Order> order = orderService.findById(id);
		model.addAttribute("details", order.get().getDetail());
		model.addAttribute("session", session.getAttribute("iduser"));
		return "user/detailshopping";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("iduser");
		return "redirect:/";
	}
}
