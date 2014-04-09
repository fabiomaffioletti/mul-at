package com.at.mul.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.at.mul.domain.order.Order;

public class OrderRowMapper implements RowMapper<Order> {

	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order order = new Order();
		order.setId(rs.getInt("id"));
		order.setCode(rs.getInt("code"));
		order.setQuantity(rs.getInt("quantity"));
		return order;
	}

}
