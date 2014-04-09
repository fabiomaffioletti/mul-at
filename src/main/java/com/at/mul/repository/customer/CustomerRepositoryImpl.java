package com.at.mul.repository.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.at.mul.domain.customer.Customer;
import com.at.mul.mapper.CustomerRowMapper;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
	
	@Autowired
	private JdbcTemplate customerJdbcTemplate;

	@Override
	public Customer save(Customer customer) {
		customerJdbcTemplate.update("INSERT INTO customer (name, age) VALUES (?, ?)", customer.getName(), customer.getAge());
		return getByName(customer.getName());
	}

	@Override
	public Customer getByName(String name) {
		return customerJdbcTemplate.queryForObject("SELECT * FROM customer WHERE name = '" + name + "'", new CustomerRowMapper());
	}

}
