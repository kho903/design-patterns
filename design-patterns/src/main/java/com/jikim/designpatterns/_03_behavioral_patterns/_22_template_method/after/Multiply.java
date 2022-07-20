package com.jikim.designpatterns._03_behavioral_patterns._22_template_method.after;

public class Multiply extends FileProcessor {

	public Multiply(String path) {
		super(path);
	}

	@Override
	protected int getResult(int result, int number) {
		if (result == 0) result = 1;
		return result *= number;
	}
}
