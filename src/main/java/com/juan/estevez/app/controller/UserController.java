package com.juan.estevez.app.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.juan.estevez.app.model.User;
import com.juan.estevez.app.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;
	
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
	
	@GetMapping("/compra")
	public String obtenerCompras(HttpSession session, Model model) {
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		return "user/compras";
	}
}
