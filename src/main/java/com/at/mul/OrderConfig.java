package com.at.mul;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.atomikos.jdbc.AtomikosDataSourceBean;

@Configuration
@DependsOn("transactionManager")
public class OrderConfig {
	
	@Autowired
	private JpaVendorAdapter jpaVendorAdapter;

	@Bean(name = "orderDataSource", initMethod = "init", destroyMethod = "close")
    public DataSource orderDataSource() {
		JdbcDataSource h2XaDataSource = new JdbcDataSource();
		h2XaDataSource.setURL("jdbc:h2:order");

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(h2XaDataSource);
        xaDataSource.setUniqueResourceName("xads2");
        return xaDataSource;
    }
	
	@Bean(name = "entityManagerFactoryB")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryB() {
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource(orderDataSource());
		entityManager.setJpaVendorAdapter(jpaVendorAdapter);
		entityManager.setPersistenceUnitName("orderPersistenceUnit");
		entityManager.setPersistenceXmlLocation("classpath*:persistence.xml");
		return entityManager;
	}

}
