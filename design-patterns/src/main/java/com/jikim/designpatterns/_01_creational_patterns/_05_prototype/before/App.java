package com.jikim.designpatterns._01_creational_patterns._05_prototype.before;

public class App {
	public static void main(String[] args) {
		GithubRepository repository = new GithubRepository();
		repository.setUser("whiteShip");
		repository.setName("live-study");

		GithubIssue githubIssue = new GithubIssue(repository);
		githubIssue.setId(1);
		githubIssue.setTitle("1주차 과제: JVM은 무엇이며 자바 코드는 어떻게 실행하는 것인가.");

		String url = githubIssue.getUrl();
		System.out.println(url);

		/*GithubIssue githubIssue2 = new GithubIssue(repository);
		githubIssue2.setId(2);
		githubIssue2.setTitle("2주차 과제: ...");*/

		// GithubIssue clone = githubIssue.clone();
		// clone != githubIssue
		// clone.equals(githubIssue) => true
	}
}
