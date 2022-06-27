package com.jikim.designpatterns._01_creational_patterns._01_singleton.v6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class App {
	public static void main(String[] args) throws
		NoSuchMethodException,
		InvocationTargetException,
		InstantiationException,
		IllegalAccessException, IOException, ClassNotFoundException {

		Settings settings = Settings.INSTANCE;
		/*Settings settings1 = Settings.INSTANCE;
		System.out.println(settings == settings1); // true*/

		// enum은 리플렉션 사용 불가
		/*Constructor<Settings> constructor = Settings.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		Settings settings1 = constructor.newInstance();*/

		/*Settings settings1 = null;
		Constructor<?>[] declaredConstructors = Settings.class.getDeclaredConstructors();
		for (Constructor<?> declaredConstructor : declaredConstructors) {
			declaredConstructor.setAccessible(true);
			settings1 = (Settings) declaredConstructor.newInstance("INSTANCE");
		}

		System.out.println(settings == settings1);*/

		// 직렬화 / 역직렬화
		Settings settings1 = null;
		try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream("settings.obj"))) {
			out.writeObject(settings);
		}

		try (ObjectInput in = new ObjectInputStream(new FileInputStream("settings.obj"))) {
			settings1 = (Settings) in.readObject();
		}

		System.out.println(settings == settings1);
	}
}
