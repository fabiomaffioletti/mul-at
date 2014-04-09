package com.at.mul.repository.order;

import com.at.mul.domain.order.Order;

public interface OrderRepository {
	
	Order save(Order order);
	
	Order getByCode(Integer code);

}
