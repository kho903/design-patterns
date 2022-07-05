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

## 적용 후
CommentService interface
```java
package com.jikim.designpatterns._02_structural_patterns._09_decorator.after;

public interface CommentService {

	void addComment(String comment);
}
```
CommentDecorator
```java
package com.jikim.designpatterns._02_structural_patterns._09_decorator.after;

public class CommentDecorator implements CommentService {

	private CommentService commentService;

	public CommentDecorator(CommentService commentService) {
		this.commentService = commentService;
	}

	@Override
	public void addComment(String comment) {
		commentService.addComment(comment);
	}
}
```
DefaultCommentService
```java
package com.jikim.designpatterns._02_structural_patterns._09_decorator.after;

public class DefaultCommentService implements CommentService {
	@Override
	public void addComment(String comment) {
		System.out.println(comment);
	}
}
```
TrimmingCommentDecorator
```java
package com.jikim.designpatterns._02_structural_patterns._09_decorator.after;

public class TrimmingCommentDecorator extends CommentDecorator {
	public TrimmingCommentDecorator(CommentService commentService) {
		super(commentService);
	}

	@Override
	public void addComment(String comment) {
		super.addComment(trim(comment));
	}

	private String trim(String comment) {
		return comment.replace("...", "");
	}
}
```
SpamFilteringCommentDecorator
```java
package com.jikim.designpatterns._02_structural_patterns._09_decorator.after;

public class SpamFilteringCommentDecorator extends CommentDecorator {
	public SpamFilteringCommentDecorator(CommentService commentService) {
		super(commentService);
	}

	@Override
	public void addComment(String comment) {
		if (!isSpam(comment))
			super.addComment(comment);
	}

	private boolean isSpam(String comment) {
		return comment.contains("http");
	}
}
```
Client
```java
package com.jikim.designpatterns._02_structural_patterns._09_decorator.after;

public class Client {

	private CommentService commentService;

	public Client(CommentService commentService) {
		this.commentService = commentService;
	}

	public void writeComment(String comment) {
		commentService.addComment(comment);
	}
}
```
App
```java
package com.jikim.designpatterns._02_structural_patterns._09_decorator.after;

public class App {

	private static boolean enabledSpamFilter = true;

	private static boolean enabledTrimming = true;

	public static void main(String[] args) {
		CommentService commentService = new DefaultCommentService();
		if (enabledSpamFilter) {
			commentService = new SpamFilteringCommentDecorator(commentService);
		}

		if (enabledTrimming) {
			commentService = new TrimmingCommentDecorator(commentService);
		}

		Client client = new Client(commentService);
		client.writeComment("오징어 게임");
		client.writeComment("보는게 하는거 보다 재밌을 수가 없지...");
		client.writeComment("http://whiteship.me");
	}
}
```

## 장점과 단점
### 장점
- 새로운 클래스를 만들지 않고 기존 기능을 조합할 수 있다.
- 컴파일 타임이 아닌 런타임에 동적으로 기능을 변경할 수 있다.

### 단점
- 데코레이터를 조합하는 코드가 복잡할 수 있다.

## 실무에서 어떻게 쓰이나?
### 자바
- InputStream, OutputStream, Reader, Writer의 생성자를 활용한 랩퍼
- java.util.Collections가 제공하는 메소드들 활용한 랩퍼
- javax.servlet.http.HttpServletRequest/ResponseWrapper

### 스프링
- ServerHttpRequestDecorator
