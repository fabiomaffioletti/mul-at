package com.at.mul;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import com.at.mul.domain.customer.Customer;
import com.at.mul.domain.order.Order;
import com.at.mul.exception.NoRollbackException;
import com.at.mul.exception.StoreException;
import com.at.mul.repository.customer.CustomerRepository;
import com.at.mul.repository.order.OrderRepository;
import com.at.mul.service.StoreService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MainConfig.class)
@TransactionConfiguration(transactionManager = "transactionManager")
public class StoreServiceTest {

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Autowired
	private StoreService storeService;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Test
	@Transactional
	public void testStore() throws Exception {
		Customer c = new Customer();
		c.setName("test");
		c.setAge(30);

		Order o = new Order();
		o.setCode(1);
		o.setQuantity(7);

		storeService.store(c, o);

		Assert.assertNotNull(c.getId());
		Assert.assertNotNull(o.getId());

		Assert.assertEquals(1, customerRepository.findAll().size());
		Assert.assertEquals(1, orderRepository.findAll().size());
	}

	@Test(expected = StoreException.class)
	public void testStoreWithStoreException() throws StoreException {
		Customer c = new Customer();
		c.setName("test");
		c.setAge(30);

		Order o = new Order();
		o.setCode(1);
		o.setQuantity(7);

		Assert.assertEquals(0, customerRepository.findAll().size());
		Assert.assertEquals(0, orderRepository.findAll().size());

		storeService.storeWithStoreException(c, o);
	}

	@Test(expected = NoRollbackException.class)
	@Transactional
	public void testStoreWithNoRollbackException() throws NoRollbackException {
		Customer c = new Customer();
		c.setName("test");
		c.setAge(30);

		Order o = new Order();
		o.setCode(1);
		o.setQuantity(7);

		Assert.assertEquals(0, customerRepository.findAll().size());
		Assert.assertEquals(0, orderRepository.findAll().size());

		try {
			storeService.storeWithNoRollbackException(c, o);
		} catch (NoRollbackException e) {
			e.printStackTrace();
			Assert.assertEquals(1, customerRepository.findAll().size());
			Assert.assertEquals(1, orderRepository.findAll().size());
			throw e;
		}
	}

}
