package com.jikim.designpatterns._01_creational_patterns._04_builder.java;

import lombok.Builder;

@Builder
public class LombokExample {

	private String title;

	private int nights;

	private int days;

	@Override
	public String toString() {
		return "LombokExample{" +
			"title='" + title + '\'' +
			", nights=" + nights +
			", days=" + days +
			'}';
	}

	public static void main(String[] args) {
		LombokExample trip = LombokExample.builder()
			.title("여행")
			.nights(2)
			.days(3)
			.build();
		System.out.println(trip);
	}
}
