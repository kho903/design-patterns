# 이터레이터 (Interator) 패턴
집합 객체 내부 구조를 노출시키지 않고 순회하는 방법을 제공하는 패턴
- 집합 객체를 순회하는 클라이언트 코드를 변경하지 않고 다양한 순회 방법을 제공할 수 있다.

## 적용 전
Post
```java
package com.jikim.designpatterns._03_behavioral_patterns._16_iterator.before;

import java.time.LocalDateTime;

public class Post {
	private String title;

	private LocalDateTime createdDateTime;

	public Post(String title) {
		this.title = title;
		this.createdDateTime = LocalDateTime.now();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
}
```
Board
```java
package com.jikim.designpatterns._03_behavioral_patterns._16_iterator.before;

import java.util.ArrayList;
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
}
```
Client
```java
package com.jikim.designpatterns._03_behavioral_patterns._16_iterator.before;

import java.util.Collections;
import java.util.List;

public class Client {

	public static void main(String[] args) {
		Board board = new Board();
		board.addPost("디자인 패턴 게임");
		board.addPost("선생님, 저랑 디자인 패턴 하나 학습하시겠습니까?");
		board.addPost("지금 이 자리에 계신 여러분들은 모두 디자인 패턴을 학습하고 계신 분들입니다.");

		// TODO 들어간 순서대로 순회하기
		List<Post> posts = board.getPosts();
		for (int i = 0; i < posts.size(); i++) {
			Post post = posts.get(i);
			System.out.println(post.getTitle());
		}

		System.out.println();

		// TODO 가장 최신 글 먼저 순회하기
		Collections.sort(posts, (p1, p2) -> p2.getCreatedDateTime().compareTo(p1.getCreatedDateTime()));
		for (int i = 0; i < posts.size(); i++) {
			Post post = posts.get(i);
			System.out.println(post.getTitle());
		}
	}
}
```

## 적용 후
### V1
Board
```java
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
```
Client
```java
package com.jikim.designpatterns._03_behavioral_patterns._16_iterator.after;

import java.util.Iterator;

public class Client {
	public static void main(String[] args) {
		Board board = new Board();
		board.addPost("디자인 패턴 게임");
		board.addPost("선생님, 저랑 디자인 패턴 하나 학습하시겠습니까?");
		board.addPost("지금 이 자리에 계신 여러분들은 모두 디자인 패턴을 학습하고 계신 분들입니다.");

		// 들어간 순서대로 순회
		// Iterator<Post> iterator = board.getPosts().iterator();
		Iterator<Post> iterator = board.getDefaultIterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getTitle());
		}
	}
}
```

### V2
RecentPostIterator
```java
package com.jikim.designpatterns._03_behavioral_patterns._16_iterator.after_v2;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RecentPostIterator implements Iterator<Post> {

	private Iterator<Post> internalIterator;
	
	public RecentPostIterator(List<Post> posts) {
		Collections.sort(posts, (p1, p2) -> p2.getCreatedDateTime().compareTo(p1.getCreatedDateTime()));
		this.internalIterator = posts.iterator();
	}

	@Override
	public boolean hasNext() {
		return this.internalIterator.hasNext();
	}

	@Override
	public Post next() {
		return this.internalIterator.next();
	}
}
```
Client
```java
package com.jikim.designpatterns._03_behavioral_patterns._16_iterator.after_v2;

import java.util.Iterator;
import java.util.List;

public class Client {
	public static void main(String[] args) {
		Board board = new Board();
		board.addPost("디자인 패턴 게임");
		board.addPost("선생님, 저랑 디자인 패턴 하나 학습하시겠습니까?");
		board.addPost("지금 이 자리에 계신 여러분들은 모두 디자인 패턴을 학습하고 계신 분들입니다.");

		// 들어간 순서대로 순회하기
		List<Post> posts = board.getPosts();
		Iterator<Post> iterator = posts.iterator();
		System.out.println(iterator.getClass());
		for (int i = 0; i < posts.size(); i++) {
			Post post = posts.get(i);
			System.out.println(post.getTitle());
		}

		// 가장 최신 글 먼저 순회하기
		Iterator<Post> recentPostIterator = board.getRecentPostIterator();
		while (recentPostIterator.hasNext()) {
			System.out.println(recentPostIterator.next().getTitle());
		}
	}
}
```
