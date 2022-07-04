package com.jikim.designpatterns._02_structural_patterns._06_adapter.after_v2;

import com.jikim.designpatterns._02_structural_patterns._06_adapter.after_v2.security.UserDetails;
import com.jikim.designpatterns._02_structural_patterns._06_adapter.after_v2.security.UserDetailsService;

public class AccountService implements UserDetailsService {

	public Account findAccountByUsername(String username) {
		Account account = new Account();
		account.setName(username);
		account.setPassword(username);
		account.setEmail(username);
		return account;
	}

	public void createNewAccount(Account account) {

	}

	public void updateAccount(Account account) {

	}

	@Override
	public UserDetails loadUser(String username) {
		return findAccountByUsername(username);
	}
}
