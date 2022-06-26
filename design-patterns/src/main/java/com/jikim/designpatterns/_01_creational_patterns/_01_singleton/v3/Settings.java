package com.jikim.designpatterns._01_creational_patterns._01_singleton.v3;

public class Settings {

	// 이른 초기화 (eager initialization)
	private static final Settings INSTANCE = new Settings();

	private Settings() {}

	public static Settings getInstance() {
		return INSTANCE;
	}
	
}
