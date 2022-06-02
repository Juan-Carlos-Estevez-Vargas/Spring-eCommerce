package com.juan.estevez.app.service;

import java.util.List;

import com.juan.estevez.app.model.Order;

public interface IOrderService {
	
	List<Order> findAll();

	Order save(Order order);
	
	String generateOrderNumber(int num);
}
