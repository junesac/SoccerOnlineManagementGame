package com.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.service.UserService;
import com.service.impl.UserServiceImpl;

@Configuration
public class AppConfig {

	@Autowired
	private DataSource dataSource;

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}

	@Bean
	public UserDao userDao() {
		return new UserDaoImpl();
	}

}
