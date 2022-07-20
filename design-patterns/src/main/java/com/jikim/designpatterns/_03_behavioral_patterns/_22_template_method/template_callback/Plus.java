package com.jikim.designpatterns._03_behavioral_patterns._22_template_method.template_callback;

public class Plus implements Operator {
	@Override
	public int getResult(int result, int number) {
		return result += number;
	}
}
