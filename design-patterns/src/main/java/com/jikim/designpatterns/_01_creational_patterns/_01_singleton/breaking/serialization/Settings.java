package com.jikim.designpatterns._01_creational_patterns._01_singleton.breaking.serialization;

import java.io.Serializable;

public class Settings implements Serializable {

	private Settings() {}

	private static class SettingsHolder {
		private static final Settings INSTANCE = new Settings();
	}

	public static Settings getInstance() {
		return SettingsHolder.INSTANCE;
	}

	// 직렬화, 역직렬화 해결 방법
	protected Object readResolve() {
		return getInstance();
	}
}
