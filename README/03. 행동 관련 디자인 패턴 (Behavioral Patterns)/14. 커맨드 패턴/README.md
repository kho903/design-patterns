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

## 적용 후
Game, Light 그대로

Command interface
```java
package com.jikim.designpatterns._03_behavioral_patterns._14_command.after;

public interface Command {

	void execute();
}
```
LightOnCommand
```java
package com.jikim.designpatterns._03_behavioral_patterns._14_command.after;

public class LightOnCommand implements Command {

	private Light light;

	public LightOnCommand(Light light) {
		this.light = light;
	}

	@Override
	public void execute() {
		light.on();
	}
}
```
LightOffCommand
```java
package com.jikim.designpatterns._03_behavioral_patterns._14_command.after;

public class LightOffCommand implements Command {

	private Light light;

	public LightOffCommand(Light light) {
		this.light = light;
	}

	@Override
	public void execute() {
		light.off();
	}
}
```
GameStartCommand
```java
package com.jikim.designpatterns._03_behavioral_patterns._14_command.after;

public class GameStartCommand implements Command {

	private Game game;

	public GameStartCommand(Game game) {
		this.game = game;
	}

	@Override
	public void execute() {
		game.start();
	}
}
```
GameEndCommand
```java
package com.jikim.designpatterns._03_behavioral_patterns._14_command.after;

public class GameEndCommand implements Command {

	private Game game;

	public GameEndCommand(Game game) {
		this.game = game;
	}

	@Override
	public void execute() {
		game.end();
	}
}
```
Button
```java
package com.jikim.designpatterns._03_behavioral_patterns._14_command.after;

public class Button {

	private Command command;

	public Button(Command command) {
		this.command = command;
	}

	public void press() {
		command.execute();
	}

	public static void main(String[] args) {
		Button button = new Button(new LightOnCommand(new Light()));
		button.press();
	}
}
```
MyApp
```java
package com.jikim.designpatterns._03_behavioral_patterns._14_command.after;

public class MyApp {

	private Command command;

	public MyApp(Command command) {
		this.command = command;
	}

	public void press() {
		command.execute();
	}

	public static void main(String[] args) {
		MyApp myApp = new MyApp(new GameStartCommand(new Game()));
		myApp.press();
	}
}
```

## 장점과 단점
### 장점
- 기존 코드를 변경하지 않고 새로운 커맨드를 만들 수 있다.
  - OCP
- 수신자의 코드가 변경되어도 호출자의 코드는 변경되지 않는다.
  - SRP
- 커맨드 객체를 로깅, DB에 저장, 네트워크로 전송하는 등 다양한 방법으로 활용할 수도 있다.

### 단점
- 코드가 복잡하고 클래스가 많아진다.

## 자바와 스프링에서 찾아보는 패턴
### 자바
- Runnable
- 람다
- 메소드 레퍼런스

### 스프링
- SimpleJdbcInsert
- SimpleJdbcCall
