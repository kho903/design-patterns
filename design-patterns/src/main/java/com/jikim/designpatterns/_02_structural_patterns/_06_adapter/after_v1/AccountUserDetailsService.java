package com.jikim.designpatterns._02_structural_patterns._06_adapter.after_v1;

import com.jikim.designpatterns._02_structural_patterns._06_adapter.after_v1.security.UserDetails;
import com.jikim.designpatterns._02_structural_patterns._06_adapter.after_v1.security.UserDetailsService;

public class AccountUserDetailsService implements UserDetailsService {

	AccountService accountService;

	public AccountUserDetailsService(AccountService accountService) {
		this.accountService = accountService;
	}

	@Override
	public UserDetails loadUser(String username) {
		Account account = accountService.findAccountByUsername(username);
		return new AccountUserDetails(account);
	}
}
