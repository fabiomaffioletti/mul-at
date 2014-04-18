package com.at.mul.service;

import com.at.mul.domain.customer.Customer;
import com.at.mul.domain.order.Order;
import com.at.mul.exception.NoRollbackException;
import com.at.mul.exception.StoreException;

public interface StoreService {
	
	void store(Customer customer, Order order) throws Exception;
	
	void storeWithStoreException(Customer customer, Order order) throws StoreException;
	
	void storeWithNoRollbackException(Customer customer, Order order) throws NoRollbackException;

}
