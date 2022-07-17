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

		chatServer.unregister("디자인 패턴", user2);

		chatServer.sendMessage(user2, "디자인 패턴", "옵저버 패턴");	}
}
