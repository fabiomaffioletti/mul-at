package com.at.mul;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.at.mul.domain.customer.Customer;
import com.at.mul.domain.order.Order;
import com.at.mul.repository.customer.CustomerRepository;
import com.at.mul.repository.order.OrderRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MainConfig.class)
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager")
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Test
	public void testCustomerConfig() {

	}

	@Test
	public void save() {
		System.out.println("###############################################################");
		System.out.println("###############################################################");
		System.out.println("###############################################################");
		Customer c = new Customer();
		c.setName("test-name");
		c.setAge(30);
		Customer cust = customerRepository.save(c);
		Assert.assertNotNull(cust.getId());
		System.out.println(cust);
		
		Order o = new Order();
		o.setCode(1);
		o.setQuantity(10);
		Order ord = orderRepository.save(o);
		Assert.assertNotNull(ord.getId());
		System.out.println(ord);
	}

}
