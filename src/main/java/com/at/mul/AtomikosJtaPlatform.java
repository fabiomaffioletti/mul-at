package com.at.mul;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;

public class AtomikosJtaPlatform extends AbstractJtaPlatform {

	private static final long serialVersionUID = 1L;

	static TransactionManager transactionManager;
	static UserTransaction transaction;

	@Override
	protected TransactionManager locateTransactionManager() {
		return transactionManager;
	}

	@Override
	protected UserTransaction locateUserTransaction() {
		return transaction;
	}
}
