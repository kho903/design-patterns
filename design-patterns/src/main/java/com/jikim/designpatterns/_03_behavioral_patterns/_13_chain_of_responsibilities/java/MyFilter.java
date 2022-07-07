package com.jikim.designpatterns._03_behavioral_patterns._13_chain_of_responsibilities.java;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = "/hello")
public class MyFilter implements Filter {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
		IOException, ServletException {
		System.out.println("게임에 참여하신 여러분 모두 진심으로 환영합니다.");
		filterChain.doFilter(servletRequest, servletResponse);
		System.out.println("꽝!");
	}
}
