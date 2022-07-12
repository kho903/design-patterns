package com.jikim.designpatterns._03_behavioral_patterns._16_iterator.after;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Board {

	List<Post> posts = new ArrayList<>();

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public void addPost(String content) {
		this.posts.add(new Post(content));
	}

	public Iterator<Post> getDefaultIterator() {
		return posts.iterator();
	}
}