package com.jikim.designpatterns._03_behavioral_patterns._13_chain_of_responsibilities.java;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CoRInJava {
	public static void main(String[] args) {
		Filter filter = new Filter() {
			@Override
			public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
				FilterChain filterChain) throws IOException, ServletException {
				// TODO 전처리
				filterChain.doFilter(servletRequest, servletResponse);
				// TODO 후처리
			}
		};
	}
}
