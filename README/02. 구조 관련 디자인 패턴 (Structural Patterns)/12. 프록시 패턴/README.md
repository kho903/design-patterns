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