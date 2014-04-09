package com.at.mul.repository.order;

import java.util.List;

import com.at.mul.domain.order.Order;

public interface OrderRepository {
	
	Order save(Order order);
	
	List<Order> findAll();

}
