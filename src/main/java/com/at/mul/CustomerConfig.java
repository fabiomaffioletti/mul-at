package com.at.mul;

import java.util.HashMap;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.atomikos.jdbc.AtomikosDataSourceBean;

@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = "com.at.mul.repository.customer",
		entityManagerFactoryRef = "customerEntityManager", transactionManagerRef = "transactionManager")
public class CustomerConfig {

	@Autowired private JpaVendorAdapter jpaVendorAdapter;

	@Bean(name = "customerDataSource", initMethod = "init", destroyMethod = "close")
	public DataSource customerDataSource() {
		JdbcDataSource h2XaDataSource = new JdbcDataSource();
		h2XaDataSource.setURL("jdbc:h2:customer");

		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(h2XaDataSource);
		xaDataSource.setUniqueResourceName("xads1");
		return xaDataSource;
	}

	@Bean(name = "customerEntityManager")
	@DependsOn("transactionManager")
	public LocalContainerEntityManagerFactoryBean customerEntityManager() throws Throwable {

		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
		properties.put("javax.persistence.transactionType", "JTA");

		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource(customerDataSource());
		entityManager.setJpaVendorAdapter(jpaVendorAdapter);
		entityManager.setPackagesToScan("com.at.mul.domain.customer");
		entityManager.setPersistenceUnitName("customerPersistenceUnit");
		entityManager.setJpaPropertyMap(properties);
		return entityManager;
	}

}
