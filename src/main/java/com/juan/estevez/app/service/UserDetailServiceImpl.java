package com.juan.estevez.app.service;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	@Autowired
	HttpSession session;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<com.juan.estevez.app.model.User> optionalUser = userService.findByEmail(username);
		if (optionalUser.isPresent()) {
			session.setAttribute("idusuario", optionalUser.get().getId());
			com.juan.estevez.app.model.User user = optionalUser.get();
			return User.builder().username(user.getName()).password(bCrypt.encode(user.getPassword())).roles(user.getType()).build();
		}else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
	}
	
}
