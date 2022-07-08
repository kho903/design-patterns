# 커맨드 패턴
요청을 캡슐화하여 호출자 (invoker)와 수신자 (receiver)를 분리하는 패턴
- 요청을 처리하는 방법이 바뀌더라도, 호출자의 코드는 변경되지 않는다.

## 적용 전
```java
package com.jikim.designpatterns._03_behavioral_patterns._14_command.before;

public class Light {

	private boolean isOn;

	public void on() {
		System.out.println("불을 켭니다.");
		this.isOn = true;
	}

	public void off() {
		System.out.println("불을 끕니다.");
		this.isOn = false;
	}

	public boolean isOn() {
		return this.isOn;
	}
}
```
Game
```java
package com.jikim.designpatterns._03_behavioral_patterns._14_command.before;

public class Game {

	private boolean isStarted;

	public void start() {
		System.out.println("게임을 시작합니다.");
		this.isStarted = true;
	}

	public void end() {
		System.out.println("게임을 종료합니다.");
		this.isStarted = false;
	}

	public boolean isStarted() {
		return isStarted;
	}
}
```
Button
```java
package com.jikim.designpatterns._03_behavioral_patterns._14_command.before;

public class Button {

	private Light light;

	public Button(Light light) {
		this.light = light;
	}

	public void press() {
		// light.on();
		light.off();
	}

	public static void main(String[] args) {
		Button button = new Button(new Light());
		button.press();
		button.press();
		button.press();
		button.press();
	}
}
```
MyApp
```java
package com.jikim.designpatterns._03_behavioral_patterns._14_command.before;

public class MyApp {

	// private Light light;
	//
	// public MyApp(Light light) {
	// 	this.light = light;
	// }
	// public void press() {
	// 	light.off();
	// }
	private Game game;

	public MyApp(Game game) {
		this.game = game;
	}

	public void press() {
		game.start();
	}

	public static void main(String[] args) {
		MyApp myApp = new MyApp(new Game());
		myApp.press();
		myApp.press();
		myApp.press();
	}
}
```
