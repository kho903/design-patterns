package com.jikim.designpatterns._02_structural_patterns._06_adapter.after_v1;

import com.jikim.designpatterns._02_structural_patterns._06_adapter.after_v1.security.UserDetails;

public class AccountUserDetails implements UserDetails {

	private Account account;

	public AccountUserDetails(Account account) {
		this.account = account;
	}

	@Override
	public String getUsername() {
		return this.account.getName();
	}

	@Override
	public String getPassword() {
		return this.account.getPassword();
	}
}
