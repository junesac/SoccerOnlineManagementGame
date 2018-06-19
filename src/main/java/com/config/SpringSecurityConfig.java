package com.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		// 1. In memory Authentication

		// auth.inMemoryAuthentication().withUser("sachin").password("{noop}password").roles("USER");
		// auth.inMemoryAuthentication().withUser("admin").password("{noop}password").roles("ADMIN");
		// auth.inMemoryAuthentication().withUser("dba").password("{noop}password").roles("MANAGER");

		// 2. jdbc authentication

		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery(
						"select username, password, enabled from users1 " + "where lower(username)=lower(?)")
				.authoritiesByUsernameQuery("select U.username, R.ROLENAME from users1 u, roles r, user_role ur "
						+ "where u.userid =ur.userid and r.rid = ur.rid and lower(username) = lower(?)")
				.passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// 1. In memory Authentication

		// http.authorizeRequests().antMatchers("/activity/**").access("hasRole('ROLE_ADMIN')
		// or hasRole('ROLE_USER')")
		// .antMatchers("/info/**").access("hasRole('ROLE_ADMIN') or
		// hasRole('ROLE_MANAGER')").and().formLogin();

		// 2. jdbc authentication

		http.authorizeRequests().antMatchers("/registration/**", "/login/**").permitAll().antMatchers("/team/**")
				.access("hasRole('ADMIN') or hasRole('USER')").antMatchers("/player/**")
				.access("hasRole('ADMIN') or hasRole('USER')").anyRequest().authenticated().and().csrf().disable()
				.httpBasic();

	}

}
