package com.jikim.designpatterns._02_structural_patterns._06_adapter.after_v1;

import com.jikim.designpatterns._02_structural_patterns._06_adapter.after_v1.security.LoginHandler;
import com.jikim.designpatterns._02_structural_patterns._06_adapter.after_v1.security.UserDetailsService;

public class App {
	public static void main(String[] args) {
		AccountService accountService = new AccountService();
		UserDetailsService userDetailsService = new AccountUserDetailsService(accountService);
		LoginHandler loginHandler = new LoginHandler(userDetailsService);
		String login = loginHandler.login("keesun", "keesun");
		System.out.println(login);
	}
}
