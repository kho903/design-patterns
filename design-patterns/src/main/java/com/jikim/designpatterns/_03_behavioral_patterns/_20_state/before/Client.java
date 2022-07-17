package com.jikim.designpatterns._03_behavioral_patterns._20_state.before;

public class Client {

	public static void main(String[] args) {
		Student student = new Student("jikim");
		OnlineCourse onlineCourse = new OnlineCourse();

		Student kim = new Student("kim");
		kim.addPrivateCourse(onlineCourse);

		onlineCourse.addStudent(student);
		onlineCourse.changeState(OnlineCourse.State.PRIVATE);

		onlineCourse.addStudent(kim);
		onlineCourse.addReview("hello", student);

		System.out.println(onlineCourse.getState());
		System.out.println(onlineCourse.getStudents());
		System.out.println(onlineCourse.getReviews());
	}
}
