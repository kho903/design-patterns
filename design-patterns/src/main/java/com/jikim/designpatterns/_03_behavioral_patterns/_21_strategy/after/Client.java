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
