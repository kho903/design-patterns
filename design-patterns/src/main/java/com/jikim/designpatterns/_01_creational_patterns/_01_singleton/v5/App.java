package com.jikim.designpatterns._01_creational_patterns._01_singleton.v5;

public class App {
	public static void main(String[] args) {
		// 싱글톤 패턴 적용 전
		/*Settings settings = new Settings();
		Settings settings1 = new Settings();
		System.out.println(settings == settings1);  // false */

		// 적용 후
		Settings settings = Settings.getInstance();
		Settings settings1 = Settings.getInstance();
		System.out.println(settings == settings1); // true
	}
}
