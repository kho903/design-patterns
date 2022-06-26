package com.jikim.designpatterns._01_creational_patterns._01_singleton.breaking.reflection;

public class Settings {

	private Settings() {}

	private static class SettingsHolder {
		private static final Settings INSTANCE = new Settings();
	}

	public static Settings getInstance() {
		return SettingsHolder.INSTANCE;
	}
	
}
