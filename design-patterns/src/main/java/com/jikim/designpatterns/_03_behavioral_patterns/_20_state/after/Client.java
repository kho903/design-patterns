package com.jikim.designpatterns._03_behavioral_patterns._20_state.after;

public class Client {

	public static void main(String[] args) {
		OnlineCourse onlineCourse = new OnlineCourse();
		Student student = new Student("kim");
		Student jikim = new Student("jikim");
		jikim.addPrivate(onlineCourse);

		onlineCourse.addStudent(student);

		onlineCourse.changeState(new Private(onlineCourse));

		onlineCourse.addReview("hello", student);

		onlineCourse.addStudent(jikim);

		System.out.println(onlineCourse.getState());
		System.out.println(onlineCourse.getReviews());
		System.out.println(onlineCourse.getStudents());
	}
}
