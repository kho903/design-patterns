package com.jikim.designpatterns._03_behavioral_patterns._23_visitor.after;

public interface Device {
	void print(Circle circle);

	void print(Triangle triangle);

	void print(Rectangle rectangle);

}
