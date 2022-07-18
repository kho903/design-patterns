package com.jikim.designpatterns._03_behavioral_patterns._21_strategy.after;

public class Fastest implements Speed {
	@Override
	public void blueLight() {
		System.out.println("무광꼬치");
	}

	@Override
	public void redLight() {
		System.out.println("피어씀돠");
	}
}