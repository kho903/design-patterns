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

## 적용 후
Skin interface
```java
package com.jikim.designpatterns._02_structural_patterns._07_bridge.after;

public interface Skin {
	String getName();
}
```
KDA
```java
package com.jikim.designpatterns._02_structural_patterns._07_bridge.after;

public class KDA implements Skin {
	@Override
	public String getName() {
		return "KDA";
	}
}

```
PoolParty
```java
package com.jikim.designpatterns._02_structural_patterns._07_bridge.after;

public class PoolParty implements Skin {
	@Override
	public String getName() {
		return "PoolParty";
	}
}
```
DefaultChampion
```java
package com.jikim.designpatterns._02_structural_patterns._07_bridge.after;

import com.jikim.designpatterns._02_structural_patterns._07_bridge.before.Champion;

public class DefaultChampion implements Champion {

	private Skin skin;

	private String name;

	public DefaultChampion(Skin skin, String name) {
		this.skin = skin;
		this.name = name;
	}

	@Override
	public void move() {
		System.out.printf("%s %s move\n", skin.getName(), this.name);
	}

	@Override
	public void skillQ() {
		System.out.printf("%s %s Q\n", skin.getName(), this.name);
	}

	@Override
	public void skillW() {
		System.out.printf("%s %s W\n", skin.getName(), this.name);
	}

	@Override
	public void skillE() {
		System.out.printf("%s %s E\n", skin.getName(), this.name);
	}

	@Override
	public void skillR() {
		System.out.printf("%s %s R\n", skin.getName(), this.name);
	}
}
```
아리
```java
package com.jikim.designpatterns._02_structural_patterns._07_bridge.after;

public class 아리 extends DefaultChampion {

	public 아리(Skin skin) {
		super(skin, "아리");
	}
}
```
아칼리
```java
package com.jikim.designpatterns._02_structural_patterns._07_bridge.after;

public class 아칼리 extends DefaultChampion {

	public 아칼리(Skin skin) {
		super(skin, "아칼리");
	}
}
```
App - 클라이언트 코드
```java
package com.jikim.designpatterns._02_structural_patterns._07_bridge.after;

import com.jikim.designpatterns._02_structural_patterns._07_bridge.before.Champion;

public class App {
	public static void main(String[] args) {
		Champion kda아리 = new 아리(new KDA());
		kda아리.skillQ();
		kda아리.skillW();
		kda아리.skillE();
		kda아리.skillR();

		Champion poolParty아리 = new 아리(new PoolParty());
		poolParty아리.skillQ();
		poolParty아리.skillW();
		poolParty아리.skillE();
		poolParty아리.skillR();
	}
}
```