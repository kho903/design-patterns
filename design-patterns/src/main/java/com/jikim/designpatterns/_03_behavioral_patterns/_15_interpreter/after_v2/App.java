package com.jikim.designpatterns._03_behavioral_patterns._15_interpreter.after_v2;

import java.util.Map;

public class App {
	public static void main(String[] args) {
		PostfixExpression expression = PostfixParser.parse("xyz+-a+b*");
		int result = expression.interpret(Map.of('x', 1, 'y', 2,'z',3, 'a', 3, 'b',2));
		System.out.println(result);
	}
}
