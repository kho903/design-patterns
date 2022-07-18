# 전략 (Strategy) 패턴
여러 알고리즘을 캡슐화하고 상호 교환 가능하게 만드는 패턴.
- 컨텍스트에서 사용할 알고리즘을 클라이언트가 선택한다.

## 적용 전
BlueLightRedLight
```java
package com.jikim.designpatterns._03_behavioral_patterns._21_strategy.before;

public class BlueLightRedLight {
	private int speed;

	public BlueLightRedLight(int speed) {
		this.speed = speed;
	}

	public void blueLight() {
		if (speed == 1) {
			System.out.println("무 궁 화    꽃  이");
		} else if (speed == 2) {
			System.out.println("무궁화꽃이");
		} else {
			System.out.println("무광꼬치");
		}
	}

	public void redLight() {
		if (speed == 1) {
			System.out.println("피 었 습 니  다.");
		} else if (speed == 2) {
			System.out.println("피었습니다.");
		} else {
			System.out.println("피어씀다");
		}
	}
}
```
Client
```java
package com.jikim.designpatterns._03_behavioral_patterns._21_strategy.before;

public class Client {
	public static void main(String[] args) {
		BlueLightRedLight blueLightRedLight = new BlueLightRedLight(1);
		// BlueLightRedLight blueLightRedLight = new BlueLightRedLight(2);
		// BlueLightRedLight blueLightRedLight = new BlueLightRedLight(3);
		blueLightRedLight.blueLight();
		blueLightRedLight.redLight();
	}
}
```

## 적용 후
Speed
```java
package com.jikim.designpatterns._03_behavioral_patterns._21_strategy.after;

public interface Speed {

	void blueLight();
	void redLight();
}
```
Normal
```java
package com.jikim.designpatterns._03_behavioral_patterns._21_strategy.after;

public class Normal implements Speed {
	@Override
	public void blueLight() {
		System.out.println("무 궁 화    꽃  이");
	}

	@Override
	public void redLight() {
		System.out.println("피 었 습 니  다.");
	}
}
```
Faster
```java
package com.jikim.designpatterns._03_behavioral_patterns._21_strategy.after;

public class Faster implements Speed {
	@Override
	public void blueLight() {
		System.out.println("무궁화꽃이");
	}

	@Override
	public void redLight() {
		System.out.println("피었습니다.");
	}
}
```
BlueLightRedLight
```java
package com.jikim.designpatterns._03_behavioral_patterns._21_strategy.after;

public class BlueLightRedLight {
	private Speed speed;

	public void blueLight(Speed speed) {
		speed.blueLight();
	}

	public void redLight(Speed speed) {
		speed.redLight();
	}
}
```
Client
```java
package com.jikim.designpatterns._03_behavioral_patterns._21_strategy.after;

public class Client {

	public static void main(String[] args) {
		BlueLightRedLight game = new BlueLightRedLight();
		game.blueLight(new Normal());
		game.redLight(new Faster());

		game.blueLight(new Speed() {
			@Override
			public void blueLight() {
				System.out.println("blue light");
			}

			@Override
			public void redLight() {
				System.out.println("red light");
			}
		});
		game.redLight(new Fastest());

	}
}
```
