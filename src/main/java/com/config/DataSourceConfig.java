package com.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

	@Value("${datasource.driver-class-name}")
	private String driverClassName;

	@Value("${datasource.url}")
	private String url;

	@Value("${datasource.username}")
	private String userName;

	@Value("${datasource.password}")
	private String password;

	@Bean
	public DataSource dataSource() {
		BasicDataSource driverManagerDataSource = new BasicDataSource();
		driverManagerDataSource.setDriverClassName(driverClassName);
		driverManagerDataSource.setUrl(url);
		driverManagerDataSource.setUsername(userName);
		driverManagerDataSource.setPassword(password);
		return driverManagerDataSource;
	}

}
