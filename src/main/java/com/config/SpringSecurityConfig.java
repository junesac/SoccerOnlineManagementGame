package com.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery(
						"select username, password, enabled from users1 " + "where lower(username)=lower(?)")
				.authoritiesByUsernameQuery("select U.username, R.ROLENAME from users1 u, roles r, user_role ur "
						+ "where u.userid =ur.userid and r.rid = ur.rid and lower(username) = lower(?)");
		// .passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/ws/static/**").permitAll()
				.antMatchers("/ws/team/**", "/ws/user/**", "/ws/player/**")
				.access("hasRole('ADMIN') or hasRole('USER')").anyRequest().authenticated().and().csrf().disable()
				.httpBasic();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/index.jsp");
	}

}
