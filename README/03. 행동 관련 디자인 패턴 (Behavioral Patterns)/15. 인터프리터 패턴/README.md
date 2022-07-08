# 인터프리터 (Interpreter) 페턴
자주 등장하는 문제를 간단한 언어로 정의하고 재사용하는 패턴
- 반복되는 문제 패턴을 언어 또는 문법으로 정의하고 확장할 수 있다.

## 적용 전
```java
package com.jikim.designpatterns._03_behavioral_patterns._15_interpreter.before;

import java.util.Stack;

public class PostfixNotation {

	private final String expression;

	public PostfixNotation(String expression) {
		this.expression = expression;
	}

	public static void main(String[] args) {
		PostfixNotation postfixNotation = new PostfixNotation("123+-");
		postfixNotation.calculate();
	}

	private void calculate() {
		Stack<Integer> numbers = new Stack<>();

		for (char c : this.expression.toCharArray()) {
			switch (c) {
				case '+':
					numbers.push(numbers.pop() + numbers.pop());
					break;
				case '-':
					int right = numbers.pop();
					int left = numbers.pop();
					numbers.push(left - right);
					break;
				default:
					numbers.push(Integer.parseInt(c + ""));
			}
		}
		System.out.println(numbers.pop());
	}
}
```
