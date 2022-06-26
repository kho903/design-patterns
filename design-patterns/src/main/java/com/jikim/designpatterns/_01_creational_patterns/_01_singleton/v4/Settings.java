package com.jikim.designpatterns._01_creational_patterns._01_singleton.v4;

public class Settings {

	private static volatile Settings instance;

	private Settings() {}

	public static Settings getInstance() {
		// double-checked locking
		if (instance == null) {
			synchronized (Settings.class) {
				if (instance == null) {
					instance = new Settings();
				}
			}
		}
		return instance;
	}
	
}
