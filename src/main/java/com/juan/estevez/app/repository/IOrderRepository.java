package com.juan.estevez.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juan.estevez.app.model.Order;
import com.juan.estevez.app.model.User;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer>{

	List<Order> findByUser(User user);
}
