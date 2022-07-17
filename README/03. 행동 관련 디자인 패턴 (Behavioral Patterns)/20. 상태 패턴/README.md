# 상태 (State) 패턴
객체 내부 상태 변경에 따라 객체의 행동이 달라지는 패턴
- 상태에 특화된 행동들을 분리해 낼 수 있으며, 새로운 행동을 추가하더라도 다른 행동에 영향을 주지 않는다.

## 적용 전
OnlineCourse
```java
package com.jikim.designpatterns._03_behavioral_patterns._20_state.before;

import java.util.ArrayList;
import java.util.List;

public class OnlineCourse {

	public enum State {
		DRAFT, PUBLISHED, PRIVATE
	}

	private State state = State.DRAFT;

	private List<String> reviews = new ArrayList<>();

	private List<Student> students = new ArrayList<>();

	public void addReview(String review, Student student) {
		if (this.state == State.PUBLISHED) {
			this.reviews.add(review);
		} else if (this.state == State.PRIVATE && this.students.contains(student)) {
			this.reviews.add(review);
		} else {
			throw new UnsupportedOperationException("드래프트 상태에서는 리뷰를 작성할 수 없습니다.");
		}
	}

	public void addStudent(Student student) {
		if (this.state == State.DRAFT || this.state == State.PUBLISHED) {
			this.students.add(student);
		} else if (this.state == State.PRIVATE && availableTo(student)) {
			this.students.add(student);
		} else {
			throw new UnsupportedOperationException("학생을 해당 수업에 추가할 수 없습니다.");
		}

		if (this.students.size() > 1) {
			this.state = State.PRIVATE;
		}

	}

	public void changeState(State newState) {
		this.state = newState;
	}

	public State getState() {
		return state;
	}

	public List<String> getReviews() {
		return reviews;
	}

	public List<Student> getStudents() {
		return students;
	}

	private boolean availableTo(Student student) {
		return student.isEnabledForPrivateClass(this);
	}
}
```
Student
```java
package com.jikim.designpatterns._03_behavioral_patterns._20_state.before;

import java.util.ArrayList;
import java.util.List;

public class Student {

	private String name;

	public Student(String name) {
		this.name = name;
	}

	private List<OnlineCourse> privateCourses = new ArrayList<>();

	public boolean isEnabledForPrivateClass(OnlineCourse onlineCourse) {
		return this.privateCourses.contains(onlineCourse);
	}

	public void addPrivateCourse(OnlineCourse onlineCourse) {
		this.privateCourses.add(onlineCourse);
	}

	@Override
	public String toString() {
		return "Student{" +
			"name='" + name + '\'' +
			'}';
	}
}
```
Client
```java
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
```

## 적용 후
State
```java
package com.jikim.designpatterns._03_behavioral_patterns._20_state.after;

public interface State {

	void addReview(String review, Student student);

	void addStudent(Student student);
}
```
Draft
```java
package com.jikim.designpatterns._03_behavioral_patterns._20_state.after;

public class Draft implements State {

	private OnlineCourse onlineCourse;

	public Draft(OnlineCourse onlineCourse) {
		this.onlineCourse = onlineCourse;
	}

	@Override
	public void addReview(String review, Student student) {
		throw new UnsupportedOperationException("드래프트 상태에서는 리뷰를 남길 수 없습니다.");
	}

	@Override
	public void addStudent(Student student) {
		this.onlineCourse.getStudents().add(student);
		if (this.onlineCourse.getStudents().size() > 1) {
			this.onlineCourse.changeState(new Private(this.onlineCourse));
		}
	}
}
```
Private
```java
package com.jikim.designpatterns._03_behavioral_patterns._20_state.after;

public class Private implements State {

	private OnlineCourse onlineCourse;

	public Private(OnlineCourse onlineCourse) {
		this.onlineCourse = onlineCourse;
	}

	@Override
	public void addReview(String review, Student student) {
		if (this.onlineCourse.getStudents().contains(student)) {
			this.onlineCourse.getReviews().add(review);
		} else {
			throw new UnsupportedOperationException("프라이빗 코스는 수강하는 학생만 리뷰를 남길 수 있습니다.");
		}
	}

	@Override
	public void addStudent(Student student) {
		if (student.isAvailable(this.onlineCourse)) {
			this.onlineCourse.getStudents().add(student);
		} else {
			throw new UnsupportedOperationException("프라이빗 코스를 수강할 수 없습니다.");
		}
	}
}
```
Published
```java
package com.jikim.designpatterns._03_behavioral_patterns._20_state.after;

public class Published implements State {

	private OnlineCourse onlineCourse;

	public Published(OnlineCourse onlineCourse) {
		this.onlineCourse = onlineCourse;
	}

	@Override
	public void addReview(String review, Student student) {
		this.onlineCourse.getReviews().add(review);
	}

	@Override
	public void addStudent(Student student) {
		this.onlineCourse.getStudents().add(student);
	}
}
```
OnlineCourse
```java
package com.jikim.designpatterns._03_behavioral_patterns._20_state.after;

import java.util.ArrayList;
import java.util.List;

public class OnlineCourse {

	private State state = new Draft(this);

	private List<Student> students = new ArrayList<>();

	private List<String> reviews = new ArrayList<>();

	public void addStudent(Student student) {
		this.state.addStudent(student);
	}

	public void addReview(String review, Student student) {
		this.state.addReview(review, student);
	}

	public State getState() {
		return state;
	}

	public List<Student> getStudents() {
		return students;
	}

	public List<String> getReviews() {
		return reviews;
	}

	public void changeState(State state) {
		this.state = state;
	}
}

```
Student
```java
package com.jikim.designpatterns._03_behavioral_patterns._20_state.after;

import java.util.HashSet;
import java.util.Set;

public class Student {
	private String name;

	public Student(String name) {
		this.name = name;
	}

	private Set<OnlineCourse>  onlineCourses = new HashSet<>();

	public boolean isAvailable(OnlineCourse onlineCourse) {
		return onlineCourses.contains(onlineCourse);
	}

	public void addPrivate(OnlineCourse onlineCourse) {
		this.onlineCourses.add(onlineCourse);
	}

	@Override
	public String toString() {
		return "Student{" +
			"name='" + name + '\'' +
			'}';
	}
}
```
Client
```java
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
```
