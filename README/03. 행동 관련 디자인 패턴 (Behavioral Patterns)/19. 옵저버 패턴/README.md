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
