# 프록시 (Proxy) 패턴
특정 객체에 대한 접근을 제어하거나 기능을 추가할 수 있는 패턴
- 초기화 지연, 접근 제어, 로깅, 캐싱 등 다양하게 응용해 사용할 수 있다.

## 적용 전
GameService
```java
package com.jikim.designpatterns._02_structural_patterns._12_proxy.before;

public class GameService {

	public void startGame() {
		System.out.println("이 자리에 오신 여러분을 진심으로 환영합니다.");
	}
}
```
Client
```java
package com.jikim.designpatterns._02_structural_patterns._12_proxy.before;

public class Client {

	public static void main(String[] args) {
		GameService gameService = new GameService();
		gameService.startGame();
	}
}
```

## 적용 후
### V1
GameService
```java
package com.jikim.designpatterns._02_structural_patterns._12_proxy.after_v1;

public class GameService {

	public void startGame() throws InterruptedException {
		System.out.println("이 자리에 오신 여러분을 진심으로 환영합니다.");
	}
}
```
GameServiceProxy
```java
package com.jikim.designpatterns._02_structural_patterns._12_proxy.after_v1;

public class GameServiceProxy extends GameService {

	@Override
	public void startGame() throws InterruptedException {
		long before = System.currentTimeMillis();
		super.startGame();
		System.out.println(System.currentTimeMillis() - before);
	}
}
```
Client
```java
package com.jikim.designpatterns._02_structural_patterns._12_proxy.after_v1;


public class Client {

	public static void main(String[] args) throws InterruptedException {
		GameService gameService = new GameServiceProxy();
		gameService.startGame();
	}
}
```

### V2 - interface 적용
GameService interface
```java
package com.jikim.designpatterns._02_structural_patterns._12_proxy.after_v2;

public interface GameService {
	void startGame();
}
```
DefaultGameService
```java
package com.jikim.designpatterns._02_structural_patterns._12_proxy.after_v2;

public class DefaultGameService implements GameService {

	@Override
	public void startGame() {
		System.out.println("이 자리에 오신 여러분을 진심으로 환영합니다.");
	}
}
```
GameServiceProxy
```java
package com.jikim.designpatterns._02_structural_patterns._12_proxy.after_v2;

public class GameServiceProxy implements GameService {

	private GameService gameService;

	@Override
	public void startGame() {
		long before = System.currentTimeMillis();
		if (gameService == null) {
			this.gameService = new DefaultGameService();
		}
		gameService.startGame();
		System.out.println(System.currentTimeMillis() - before);
	}
}
```
Client
```java
package com.jikim.designpatterns._02_structural_patterns._12_proxy.after_v2;

public class Client {
	public static void main(String[] args) {
		GameService gameService = new GameServiceProxy();
		gameService.startGame();
	}
}
```

## 장점과 단점
### 장점
- 기존 코드를 변경하지 않고 새로운 기능을 추가할 수 있다.
  - OCP
- 기존 코드가 해야 하는 일만 유지할 수 있다.
  - SRP
- 기능 추가 및 초기화 지연 등으로 다양하게 활용할 수 있다.

### 단점
- 코드의 복잡도가 증가한다.
