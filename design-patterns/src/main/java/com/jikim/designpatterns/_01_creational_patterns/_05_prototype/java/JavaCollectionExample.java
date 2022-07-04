package com.jikim.designpatterns._01_creational_patterns._05_prototype.java;

import java.util.ArrayList;
import java.util.List;

public class JavaCollectionExample {
	public static void main(String[] args) {
		Student keesun = new Student("keesun");
		Student whiteShip = new Student("whiteShip");
		// ArrayList<Student> students = new ArrayList<>();
		List<Student> students = new ArrayList<>();
		students.add(keesun);
		students.add(whiteShip);

		// ArrayList clone -> 자주 사용하지 않음
		// ArrayList<Student> clone = (ArrayList<Student>)students.clone();
		// System.out.println(clone);

		List<Student> clone = new ArrayList<>(students);
		System.out.println(clone);
	}
}
