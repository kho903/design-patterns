# 어댑터(Adapter) 패턴
기존 코드를 클라이언트가 사용하는 인터페이스의 구현체로 바꿔주는 패턴
- 클라이언트가 사용하는 인터페이스를 따르지 않는 기존 코드를 재사용할 수 있게 해준다.

## 적용 전
LoginHandler
```java
package com.jikim.designpatterns._02_structural_patterns._06_adapter.before.security;

public class LoginHandler {

	UserDetailsService userDetailsService;

	public LoginHandler(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	public String login(String username, String password) {
		UserDetails userDetails = userDetailsService.loadUser(username);
		if (userDetails.getPassword().equals(password)) {
			return userDetails.getUsername();
		} else {
			throw new IllegalArgumentException();
		}
	}
}

```
UserDetails
```java
package com.jikim.designpatterns._02_structural_patterns._06_adapter.before.security;

public interface UserDetails {

	String getUsername();

	String getPassword();
}
```
UserDetailsService
```java
package com.jikim.designpatterns._02_structural_patterns._06_adapter.before.security;

public interface UserDetailsService {

	UserDetails loadUser(String username);
}

```
Account
```java
package com.jikim.designpatterns._02_structural_patterns._06_adapter.before;

public class Account {

	private String name;

	private String password;

	private String email;
	
	// getter/setter
}
```
AccountService
```java
package com.jikim.designpatterns._02_structural_patterns._06_adapter.before;

public class AccountService {

	public Account findAccountByUsername(String username) {
		Account account = new Account();
		account.setName(username);
		account.setPassword(username);
		account.setEmail(username);
		return account;
	}

	public void createNewAccount(Account account) {

	}
}
```
- 호환되지 않는 Account 와 AccountService를 어떻게 연결할 것인가?

## 적용 후
### V1
AccountUserDetails
```java
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

```
AccountUserDetailsService
```java
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
```
App (Client)
```java
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
```

### V2
- V1에서 어댑터 패턴으로 해결하였지만 클래스가 너무 많아진다는 단점이 있어 이를 보완
- Account와 AccountService에서 직접 구현

Account
```java
package com.jikim.designpatterns._02_structural_patterns._06_adapter.after_v2;

import com.jikim.designpatterns._02_structural_patterns._06_adapter.after_v2.security.UserDetails;

public class Account implements UserDetails {

	private String name;

	private String password;

	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getUsername() {
		return this.name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
```
AccountService
```java
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
```

