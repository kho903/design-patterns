# 데코레이터 패턴
기존 코드를 변경하지 않고 부가 기능을 추가하는 패턴
- 상속이 아닌 위임을 사용해서 보다 유연하게 (런타임에) 부가 기능을 추가하는 것도 가능하다.

## 적용전
CommentService
```java
package com.jikim.designpatterns._02_structural_patterns._09_decorator.before;

public class CommentService {

	public void addComment(String comment) {
		System.out.println(comment);
	}
}
```
TrimmingCommentService
```java
package com.jikim.designpatterns._02_structural_patterns._09_decorator.before;

public class TrimmingCommentService extends CommentService {

	@Override
	public void addComment(String comment) {
		super.addComment(trim(comment));
	}

	private String trim(String comment) {
		return comment.replace("...", "");
	}
}
```
SpamFilteringCommentService
```java
package com.jikim.designpatterns._02_structural_patterns._09_decorator.before;

public class SpamFilteringCommentService extends CommentService {

	@Override
	public void addComment(String comment) {
		boolean isSpam = isSpam(comment);
		if (!isSpam) {
			super.addComment(comment);
		}
	}

	private boolean isSpam(String comment) {
		return comment.contains("http");
	}
}
```
Client
```java
package com.jikim.designpatterns._02_structural_patterns._09_decorator.before;

public class Client {

	private CommentService commentService;

	public Client(CommentService commentService) {
		this.commentService = commentService;
	}

	private void writeComment(String comment) {
		commentService.addComment(comment);
	}

	public static void main(String[] args) {
		// Client client = new Client(new CommentService());
		// Client client = new Client(new TrimmingCommentService());
		Client client = new Client(new SpamFilteringCommentService());
		client.writeComment("오징어게임");
		client.writeComment("보는게 하는거 보다 재밌을 수가 없지...");
		client.writeComment("http://www.whiteship.me");
	}
}
```