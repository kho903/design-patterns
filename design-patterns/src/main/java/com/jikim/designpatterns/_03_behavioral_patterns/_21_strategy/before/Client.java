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
