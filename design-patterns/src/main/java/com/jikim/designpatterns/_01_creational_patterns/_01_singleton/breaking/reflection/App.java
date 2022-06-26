package com.jikim.designpatterns._01_creational_patterns._01_singleton.breaking.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class App {
	public static void main(String[] args) throws
		NoSuchMethodException,
		InvocationTargetException,
		InstantiationException,
		IllegalAccessException {
		// 리플렉션 사용하기
		Settings settings = Settings.getInstance();
		Constructor<Settings> constructor = Settings.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		Settings settings1 = constructor.newInstance();

		System.out.println(settings == settings1); // false
	}
}
