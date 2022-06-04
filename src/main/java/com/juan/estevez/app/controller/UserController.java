package com.juan.estevez.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
}
