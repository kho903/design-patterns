package com.jikim.designpatterns._03_behavioral_patterns._23_visitor.after;

public class Client {
	public static void main(String[] args) {
		Shape rectangle = new Rectangle();
		// Device device = new Phone();
		Device device = new Pad();
		rectangle.accept(device);
	}
}
