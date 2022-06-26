package com.jikim.designpatterns._01_creational_patterns._01_singleton.v2;

public class Settings {

	private static Settings instance;

	private Settings() {}

	public static synchronized Settings getInstance() {
		if (instance == null) {
			instance = new Settings();
		}
		return instance;
	}
	
}
