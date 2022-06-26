package com.jikim.designpatterns._01_creational_patterns._01_singleton.v1;

public class Settings {

	private static Settings instance;

	private Settings() {}

	public static Settings getInstance() {
		if (instance == null) {
			instance = new Settings();
		}
		return instance;
	}
	
}
