package com.jikim.designpatterns._03_behavioral_patterns._23_visitor.before;

public class Client {
	public static void main(String[] args) {
		Shape rectangle = new Rectangle();
		rectangle.printTo(new Phone());
		rectangle.printTo(new Watch());
		Shape triangle = new Triangle();
		triangle.printTo(new Phone());
	}
}
