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
