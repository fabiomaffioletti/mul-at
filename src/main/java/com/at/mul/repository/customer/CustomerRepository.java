package com.at.mul.repository.customer;

import java.util.List;

import com.at.mul.domain.customer.Customer;

public interface CustomerRepository {

	Customer save(Customer customer);
	
	List<Customer> findAll();
	
}
