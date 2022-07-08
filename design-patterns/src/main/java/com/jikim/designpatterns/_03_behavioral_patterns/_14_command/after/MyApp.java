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
