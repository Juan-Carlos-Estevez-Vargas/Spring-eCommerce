package com.juan.estevez.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juan.estevez.app.model.OrderDetail;

@Service
public class OrderDetailServiceImpl implements IOrderDetailService {

	@Autowired
	private IOrderDetailService orderDetailRepository;

	@Override
	public OrderDetail save(OrderDetail orderDetail) {
		return orderDetailRepository.save(orderDetail);
	}

}
