package com.jikim.designpatterns._03_behavioral_patterns._13_chain_of_responsibilities.java;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().permitAll().and()
			.addFilter(new Filter() {
				@Override
				public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
					FilterChain filterChain) throws IOException, ServletException {

				}
			});
	}
}
