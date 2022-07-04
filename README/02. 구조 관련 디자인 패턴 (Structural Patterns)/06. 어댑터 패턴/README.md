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
