package com.jikim.designpatterns._03_behavioral_patterns._22_template_method.before;

public class Client {
	public static void main(String[] args) {
		FileProcessor fileProcessor = new FileProcessor("number.txt");
		MultiplyFileProcessor fileProcessor1 = new MultiplyFileProcessor("number.txt");
		int result = fileProcessor.process();
		int multiResult = fileProcessor1.process();
		System.out.println(result);
		System.out.println(multiResult);
	}
}
