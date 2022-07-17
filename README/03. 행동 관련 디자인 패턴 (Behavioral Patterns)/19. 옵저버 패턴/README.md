# 옵저버 (Observer) 패턴
다수의 객체가 특정 객체 상태 변화를 감지하고 알림을 받는 패턴
- 발행 (publish) - 구독 (subscribe) 패턴을 구현할 수 있다.

## 적용 전
ChatServer
```java
package com.jikim.designpatterns._03_behavioral_patterns._19_observer.before;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatServer {
	private Map<String, List<String>> messages;

	public ChatServer() {
		this.messages = new HashMap<>();
	}

	public void add(String subject, String message) {
		if (messages.containsKey(subject)) {
			messages.get(subject).add(message);
		} else {
			List<String> messageList = new ArrayList<>();
			messageList.add(message);
			messages.put(subject, messageList);
		}
	}

	public List<String> getMessage(String subject) {
		return messages.get(subject);
	}
}
```
User
```java
package com.jikim.designpatterns._03_behavioral_patterns._19_observer.before;

import java.util.List;

public class User {

	private ChatServer chatServer;

	public User(ChatServer chatServer) {
		this.chatServer = chatServer;
	}

	public void sendMessage(String subject, String message) {
		chatServer.add(subject, message);
	}

	public List<String> getMessage(String subject) {
		return chatServer.getMessage(subject);
	}
}
```
Client
```java
package com.jikim.designpatterns._03_behavioral_patterns._19_observer.before;

public class Client {
	public static void main(String[] args) {
		ChatServer chatServer = new ChatServer();

		User user1 = new User(chatServer);
		user1.sendMessage("디자인패턴", "이번엔 옵저버 패턴입니다.");
		user1.sendMessage("롤드컵2021", "LCK 파이팅!");

		User user2 = new User(chatServer);
		System.out.println(user2.getMessage("디자인패턴"));

		user1.sendMessage("디자인패턴", "예제 코드 보는 중...");
		System.out.println(user2.getMessage("디자인패턴"));

	}
}
```

## 적용 후
Subscriber
```java
package com.jikim.designpatterns._03_behavioral_patterns._19_observer.after;

public interface Subscriber {
	void handleMessage(String message);
}
```
User
```java
package com.jikim.designpatterns._03_behavioral_patterns._19_observer.after;

public class User implements Subscriber {

	private String name;

	public User(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void handleMessage(String message) {
		System.out.println(message);
	}
}
```
CharServer
```java
package com.jikim.designpatterns._03_behavioral_patterns._19_observer.after;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatServer {

	private Map<String, List<Subscriber>> subscribers = new HashMap<>();

	public void register(String subject, Subscriber subscriber) {
		if (this.subscribers.containsKey(subject)) {
			this.subscribers.get(subject).add(subscriber);
		} else {
			List<Subscriber> list = new ArrayList<>();
			list.add(subscriber);
			this.subscribers.put(subject, list);
		}
	}

	public void unregister(String subject, Subscriber subscriber) {
		if (this.subscribers.containsKey(subject)) {
			this.subscribers.get(subject).remove(subscriber);
		}
	}

	public void sendMessage(User user, String subject, String message) {
		if (this.subscribers.containsKey(subject)) {
			String userMessage = user.getName() + ": " + message;
			this.subscribers.get(subject).forEach(s -> s.handleMessage(userMessage));
		}
	}
}
```
Client
```java
package com.jikim.designpatterns._03_behavioral_patterns._19_observer.after;

public class Client {
	public static void main(String[] args) {
		ChatServer chatServer = new ChatServer();
		User user1 = new User("KIM");
		User user2 = new User("LEE");

		chatServer.register("오징어 게임", user1);
		chatServer.register("오징어 게임", user2);

		chatServer.register("디자인 패턴", user1);

		chatServer.sendMessage(user1, "오징어 게임", "안녕하세요.");
		chatServer.sendMessage(user2, "디자인 패턴", "옵저버 패턴임");
	}
}
```

## 장점과 단점
### 장점
- 상태를 변경하는 객체(publisher)와 변경을 감지하는 객체(subscriber)의 관계를 느슨하게 유지할 수 있다.
- Subject의 상태 변경을 주기적으로 조회하지 않고 자동으로 감지할 수 있다.
- 런타임에 옵저버를 추가하거나 제거할 수 있다.

### 단점
- 복잡도가 증가한다.
- 디수의 Observer 객체를 등록 이후 해지 않는다면 memory leak이 발생할 수도 있다.
    - Weak Reference 방법 또는 사용하지 않는 요소는 unregister하는 방법으로 해결

## 자바와 스프링에서 찾아보는 패턴
### 자바
- Observable과 Observer (자바 9부터 deprecated)
- 자바9 이후부터는
  - PropertyChangeListener, PropertyChangeEvent
  - Flow API
- SAX (Simple API for XML) 라이브러리

### 스프링
- ApplicationContext와 ApplicationEvent