package com.jikim.designpatterns._01_creational_patterns._02_factorymethod.java;

import com.jikim.designpatterns._01_creational_patterns._02_factorymethod.after_v2.BlackShip;
import com.jikim.designpatterns._01_creational_patterns._02_factorymethod.after_v2.WhiteShip;

public class SimpleFactory {
	public Object createProduct(String name) {
		if (name.equals("whiteship")) {
			return new WhiteShip();
		} else if (name.equals("blackship")) {
			return new BlackShip();
		}

		throw new IllegalArgumentException();
	}
}
