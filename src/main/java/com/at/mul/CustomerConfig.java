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
public class CustomerConfig {

	@Bean(name = "customerDataSource", initMethod = "init", destroyMethod = "close")
    public DataSource customerDataSource() {
		JdbcDataSource h2XaDataSource = new JdbcDataSource();
		h2XaDataSource.setURL("jdbc:h2:customer");
		
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(h2XaDataSource);
        xaDataSource.setUniqueResourceName("xads1");
        return xaDataSource;
    }

	@Bean(name = "customerJdbcTemplate")
	public JdbcTemplate customerJdbcTemplate() {
		return new JdbcTemplate(customerDataSource());
	}

}
