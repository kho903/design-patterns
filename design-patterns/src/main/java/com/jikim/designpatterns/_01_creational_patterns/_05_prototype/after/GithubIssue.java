package com.jikim.designpatterns._01_creational_patterns._05_prototype.after;

import java.util.Objects;

public class GithubIssue implements Cloneable {

	private int id;

	private String title;

	private GithubRepository repository;

	public GithubIssue(GithubRepository repository) {
		this.repository = repository;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public GithubRepository getRepository() {
		return repository;
	}

	public String getUrl() {
		return String.format("https://www.github.com/%s/%s/issues/%d",
			repository.getUser(),
			repository.getName(),
			this.getId()
		);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// 얕은 복사
		// return super.clone();

		// 깊은 복사
		GithubRepository repository = new GithubRepository();
		repository.setUser(this.repository.getUser());
		repository.setName(this.repository.getName());

		GithubIssue githubIssue = new GithubIssue(repository);
		githubIssue.setId(this.id);
		githubIssue.setTitle(this.title);

		return githubIssue;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		GithubIssue that = (GithubIssue)o;
		return getId() == that.getId() && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(
			getRepository(), that.getRepository());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getTitle(), getRepository());
	}
}
