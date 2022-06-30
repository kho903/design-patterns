package com.jikim.designpatterns._01_creational_patterns._04_builder.java;

import java.util.stream.Stream;

public class StreamExample {
	public static void main(String[] args) {
		/*Stream.Builder<String> stringStreamBuilder = Stream.builder();
		Stream<String> names = stringStreamBuilder.add("keesun").add("whiteShip").build();*/
		Stream<String> names = Stream.<String>builder().add("keesun").add("whiteShip").build();
		names.forEach(System.out::println);
	}
}
