package com.jikim.designpatterns._01_creational_patterns._01_singleton.v5;

public class Settings {

	// static inner 클래스
	private Settings() {}

	private static class SettingsHolder {
		private static final Settings INSTANCE = new Settings();
	}

	public static Settings getInstance() {
		return SettingsHolder.INSTANCE;
	}
	
}
