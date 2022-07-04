package com.jikim.designpatterns._02_structural_patterns._06_adapter.after_v2;

import com.jikim.designpatterns._02_structural_patterns._06_adapter.after_v2.security.LoginHandler;
import com.jikim.designpatterns._02_structural_patterns._06_adapter.after_v2.security.UserDetailsService;

public class App {
	public static void main(String[] args) {
		UserDetailsService userDetailsService = new AccountService();
		LoginHandler loginHandler = new LoginHandler(userDetailsService);
		String login = loginHandler.login("keesun", "keesun");
		System.out.println(login);
	}
}
