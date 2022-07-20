package com.jikim.designpatterns._03_behavioral_patterns._22_template_method.template_callback;

public class Client {
	public static void main(String[] args) {
		FileProcessor fileProcessor = new FileProcessor("number.txt");
		int result = fileProcessor.process((result1, number) -> result1 += number);
		int result2 = fileProcessor.process(new Plus());
		System.out.println(result);
		System.out.println(result2);
	}
}
