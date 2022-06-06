package com.juan.estevez.app.service;

import java.util.List;
import java.util.Optional;

import com.juan.estevez.app.model.Order;
import com.juan.estevez.app.model.User;

public interface IOrderService {
	
	List<Order> findAll();

	Order save(Order order);
	
	String generateOrderNumber(int num);
	
	List<Order> findByUser(User user);
	
	Optional<Order> findById(Integer id);
}
