package com.at.mul;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;

import com.atomikos.jdbc.AtomikosDataSourceBean;

@Configuration
@DependsOn("transactionManager")
public class OrderConfig {

	@Bean(name = "orderDataSource", initMethod = "init", destroyMethod = "close")
    public DataSource orderDataSource() {
		JdbcDataSource h2XaDataSource = new JdbcDataSource();
		h2XaDataSource.setURL("jdbc:h2:order");

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(h2XaDataSource);
        xaDataSource.setUniqueResourceName("xads2");
        return xaDataSource;
    }
	
	@Bean(name = "orderJdbcTemplate")
	public JdbcTemplate orderJdbcTemplate() {
		return new JdbcTemplate(orderDataSource());
	}

}
