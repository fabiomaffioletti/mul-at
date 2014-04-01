package com.at.mul.repository.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.at.mul.domain.customer.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
