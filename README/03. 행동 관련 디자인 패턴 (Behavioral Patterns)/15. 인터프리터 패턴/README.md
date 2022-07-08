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

## 적용 후
### V1
PostfixExpression
```java
package com.jikim.designpatterns._03_behavioral_patterns._15_interpreter.after;

import java.util.Map;

public interface PostfixExpression {

	int interpret(Map<Character, Integer> context);

}
```
VariableExpression
```java
package com.jikim.designpatterns._03_behavioral_patterns._15_interpreter.after;

import java.util.Map;

public class VariableExpression implements PostfixExpression {

	private Character variable;

	public VariableExpression(Character variable) {
		this.variable = variable;
	}

	@Override
	public int interpret(Map<Character, Integer> context) {
		return context.get(variable);
	}
}
```
PlusExpression
```java
package com.jikim.designpatterns._03_behavioral_patterns._15_interpreter.after;

import java.util.Map;

public class PlusExpression implements PostfixExpression {

	private PostfixExpression left, right;

	public PlusExpression(PostfixExpression left, PostfixExpression right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public int interpret(Map<Character, Integer> context) {
		return left.interpret(context) + right.interpret(context);
	}
}
```
MinusExpression
```java
package com.jikim.designpatterns._03_behavioral_patterns._15_interpreter.after;

import java.util.Map;

public class MinusExpression implements PostfixExpression {

	private PostfixExpression left, right;

	public MinusExpression(PostfixExpression left, PostfixExpression right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public int interpret(Map<Character, Integer> context) {
		return left.interpret(context) - right.interpret(context);
	}
}
```
PostfixParser
```java
package com.jikim.designpatterns._03_behavioral_patterns._15_interpreter.after;

import java.util.Stack;

public class PostfixParser {

	public static PostfixExpression parse(String expression) {
		Stack<PostfixExpression> stack = new Stack<>();
		for (char c : expression.toCharArray()) {
			stack.push(getExpression(c, stack));
		}
		return stack.pop();
	}

	private static PostfixExpression getExpression(char c, Stack<PostfixExpression> stack) {
		switch (c) {
			case '+':
				return new PlusExpression(stack.pop(), stack.pop());
			case '-':
				PostfixExpression right = stack.pop();
				PostfixExpression left = stack.pop();
				return new MinusExpression(left, right);
			default:
				return new VariableExpression(c);
		}
	}
}
```
App
```java
package com.jikim.designpatterns._03_behavioral_patterns._15_interpreter.after;

import java.util.Map;

public class App {
	public static void main(String[] args) {
		PostfixExpression expression = PostfixParser.parse("xyz+-a+");
		int result = expression.interpret(Map.of('x', 1, 'y', 2,'z',3, 'a', 4));
		System.out.println(result);
	}
}
```

### V2
PostfixExpression
```java
package com.jikim.designpatterns._03_behavioral_patterns._15_interpreter.after_v2;

import java.util.Map;

public interface PostfixExpression {

	int interpret(Map<Character, Integer> context);

	static PostfixExpression plus(PostfixExpression left, PostfixExpression right) {
		return context -> left.interpret(context) + right.interpret(context);
	}

	static PostfixExpression minus(PostfixExpression left, PostfixExpression right) {
		return context -> left.interpret(context) - right.interpret(context);
	}

	static PostfixExpression variable(Character c) {
		return context -> context.get(c);
	}
}
```
PostfixParser
```java
package com.jikim.designpatterns._03_behavioral_patterns._15_interpreter.after_v2;

import static com.jikim.designpatterns._03_behavioral_patterns._15_interpreter.after_v2.PostfixExpression.*;

import java.util.Stack;

public class PostfixParser {

	public static PostfixExpression parse(String expression) {
		Stack<PostfixExpression> stack = new Stack<>();
		for (char c : expression.toCharArray()) {
			stack.push(getExpression(c, stack));
		}
		return stack.pop();
	}

	private static PostfixExpression getExpression(char c, Stack<PostfixExpression> stack) {
		switch (c) {
			case '+':
				return plus(stack.pop(), stack.pop());
			case '-':
				PostfixExpression right = stack.pop();
				PostfixExpression left = stack.pop();
				return minus(left, right);
			default:
				return variable(c);
		}
	}
}
```
App
```java
package com.jikim.designpatterns._03_behavioral_patterns._15_interpreter.after_v2;

import java.util.Map;

public class App {
	public static void main(String[] args) {
		PostfixExpression expression = PostfixParser.parse("xyz+-a+");
		int result = expression.interpret(Map.of('x', 1, 'y', 2,'z',3, 'a', 4));
		System.out.println(result);
	}
}
```

## 장점과 단점
### 장점
- 자주 등장하는 문제 패턴을 언어와 문법으로 정의할 수 있다.
- 기존 코드를 변경하지 않고 새로운 Expression을 추가할 수 있다.

### 단점
- 복잡한 문법을 표현하려면 Expression과 Parser가 복잡해진다.

## 자바와 스프링에서 찾아보는 패턴
### 자바
- 자바 컴파일러
- 정규 표현식

### 스프링
- SpEL (스프링 Expression Language)

