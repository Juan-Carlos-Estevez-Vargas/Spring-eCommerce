package com.juan.estevez.app.service;

import java.util.List;
import java.util.Optional;

import com.juan.estevez.app.model.User;

public interface IUserService {

	List<User> findAll();
	Optional<User> findById(Integer id);
	User save (User usuario);
	Optional<User> findByEmail(String email);
}
