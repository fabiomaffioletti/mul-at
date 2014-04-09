package com.at.mul.repository.customer;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.at.mul.domain.customer.Customer;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
	
	@PersistenceContext(unitName = "customerPersistenceUnit")
	private EntityManager entityManager;

	@Override
	public Customer save(Customer customer) {
		entityManager.persist(customer);
		return customer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> findAll() {
		return entityManager.createQuery("select c from Customer c").getResultList();
	}

}
