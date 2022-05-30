package com.juan.estevez.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juan.estevez.app.model.Order;
import com.juan.estevez.app.repository.IOrderRepository;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private IOrderRepository orderRepository;
	
	@Override
	public Order save(Order order) {
		return orderRepository.save(order);
	}
	
	

}
