package com.at.mul.repository.customer;

import com.at.mul.domain.customer.Customer;

public interface CustomerRepository {

	Customer save(Customer customer);
	
	Customer getByName(String name);
	
}
