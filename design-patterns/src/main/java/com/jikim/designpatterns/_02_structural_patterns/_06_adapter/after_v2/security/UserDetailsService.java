package com.jikim.designpatterns._02_structural_patterns._06_adapter.after_v2.security;

public interface UserDetailsService {

	UserDetails loadUser(String username);
}
