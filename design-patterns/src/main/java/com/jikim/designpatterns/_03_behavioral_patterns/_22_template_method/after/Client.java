package com.jikim.designpatterns._03_behavioral_patterns._22_template_method.after;

public class Client {
	public static void main(String[] args) {
		FileProcessor fileProcessor = new Plus("number.txt");
		FileProcessor fileProcessor1 = new Multiply("number.txt");
		int result = fileProcessor.process();
		int resultMulti = fileProcessor1.process();
		System.out.println(result);
		System.out.println(resultMulti);
	}
}
