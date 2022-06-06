package com.juan.estevez.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juan.estevez.app.model.Order;
import com.juan.estevez.app.model.User;
import com.juan.estevez.app.repository.IOrderRepository;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private IOrderRepository orderRepository;

	@Override
	public Order save(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public String generateOrderNumber(int num) {
		String numeroOrden = String.valueOf(num);
		int numDigitos = numeroOrden.length(); // numero de digitos
		for (int j = numDigitos; j <= 9; j++) // añadimos los ceros
			numeroOrden = "0" + numeroOrden;

		return numeroOrden;
	}

	@Override
	public List<Order> findByUser(User user) {
		return orderRepository.findByUser(user);
	}

}
