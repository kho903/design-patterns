package com.jikim.designpatterns._03_behavioral_patterns._23_visitor.before;

public class Client {
	public static void main(String[] args) {
		Shape rectangle = new Rectangle();
		Device device = new Phone();
		rectangle.printTo(device);
		rectangle.printTo(new Watch());
		Shape triangle = new Triangle();
		triangle.printTo(new Phone());
	}
}
