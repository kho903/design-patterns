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
