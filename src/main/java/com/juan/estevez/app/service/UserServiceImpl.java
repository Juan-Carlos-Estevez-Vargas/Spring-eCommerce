package com.juan.estevez.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.juan.estevez.app.model.User;
import com.juan.estevez.app.repository.IUserRepository;

public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findById(Integer id) {
		return userRepository.findById(id);
	}

	@Override
	public User save(User usuario) {
		return userRepository.save(usuario);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
