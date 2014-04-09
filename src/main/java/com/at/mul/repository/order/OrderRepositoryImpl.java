package com.at.mul.repository.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.at.mul.domain.order.Order;
import com.at.mul.mapper.OrderRowMapper;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
	
	@Autowired
	private JdbcTemplate orderJdbcTemplate;

	@Override
	public Order save(Order order) {
		orderJdbcTemplate.update("INSERT INTO orders (code, quantity) VALUES (?, ?)", order.getCode(), order.getQuantity());
		return getByCode(order.getCode());
	}

	@Override
	public Order getByCode(Integer code) {
		return orderJdbcTemplate.queryForObject("SELECT * FROM orders WHERE code = " + code, new OrderRowMapper());
	}

}
