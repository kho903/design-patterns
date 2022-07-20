package com.jikim.designpatterns._03_behavioral_patterns._22_template_method.after;

public class Plus extends FileProcessor {

	public Plus(String path) {
		super(path);
	}

	@Override
	protected int getResult(int result, int number) {
		return result += number;
	}
}
