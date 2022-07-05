# 브릿지 (Bridge) 패턴
추상적인 것과 구체적인 것을 분리하여 연결하는 패턴
- 하나의 계층 구조일 때 보다 각기 나누었을 때 독립적인 계층 구조로 발전시킬 수 있다.

## 적용 전
Champion interface
```java
package com.jikim.designpatterns._02_structural_patterns._07_bridge.before;

public interface Champion {

	void move();

	void skillQ();

	void skillW();

	void skillE();

	void skillR();
}
```
KDAOOO
```java
package com.jikim.designpatterns._02_structural_patterns._07_bridge.before;

public class KDAOOO implements Champion {
	@Override
	public void move() {
		System.out.println("KDA OOO move");
	}

	@Override
	public void skillQ() {
		System.out.println("KDA OOO Q");
	}

	@Override
	public void skillW() {
		System.out.println("KDA OOO W");
	}

	@Override
	public void skillE() {
		System.out.println("KDA OOO E");
	}

	@Override
	public void skillR() {
		System.out.println("KDA OOO R");
	}
}
```
PoolPartyOOO
```java
package com.jikim.designpatterns._02_structural_patterns._07_bridge.before;

public class PoolPartyOOO implements Champion {
	@Override
	public void move() {
		System.out.println("PoolParty OOO move");
	}

	@Override
	public void skillQ() {
		System.out.println("PoolParty OOO Q");
	}

	@Override
	public void skillW() {
		System.out.println("PoolParty OOO W");
	}

	@Override
	public void skillE() {
		System.out.println("PoolParty OOO E");
	}

	@Override
	public void skillR() {
		System.out.println("PoolParty OOO R");
	}
}
```
App - Client
```java
public class App {
	public static void main(String[] args) {
		Champion kdaOO = new KDAOOO();
		kdaOO.skillQ();
		kdaOO.skillW();
		kdaOO.skillE();
		kdaOO.skillR();
	}
}
```
