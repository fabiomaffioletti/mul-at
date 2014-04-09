package com.at.mul.repository.order;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.at.mul.domain.order.Order;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

	@PersistenceContext(unitName = "orderPersistenceUnit")
	private EntityManager entityManager;

	@Override
	public Order save(Order order) {
		entityManager.persist(order);
		return order;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findAll() {
		return entityManager.createQuery("select c from Order c").getResultList();
	}

}
