package com.jikim.designpatterns._01_creational_patterns._05_prototype.after;

public class App {
	public static void main(String[] args) throws CloneNotSupportedException {
		GithubRepository repository = new GithubRepository();
		repository.setUser("whiteShip");
		repository.setName("live-study");

		GithubIssue githubIssue = new GithubIssue(repository);
		githubIssue.setId(1);
		githubIssue.setTitle("1주차 과제: JVM은 무엇이며 자바 코드는 어떻게 실행하는 것인가.");

		String url = githubIssue.getUrl();
		System.out.println(url);

		GithubIssue clone = (GithubIssue)githubIssue.clone();
		System.out.println(clone.getUrl());

		// clone을 있는 그대로 overriding 하면 얕은 복사...
		repository.setUser("Keesun");

		System.out.println(clone != githubIssue);
		System.out.println(clone.equals(githubIssue));
		System.out.println(clone.getClass() == githubIssue.getClass());
		System.out.println(clone.getRepository() == githubIssue.getRepository());

		// super.clone 시 Keesun
		// deep copy 시 whiteShip
		System.out.println(clone.getUrl());
	}
}
