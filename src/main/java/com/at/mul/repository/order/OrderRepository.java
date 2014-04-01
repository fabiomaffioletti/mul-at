package com.at.mul.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.at.mul.domain.order.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
