# 책임 연쇄 패턴 (Chain-of-Responsibility) 패턴
요청을 보내는 쪽 (sender)과 요청을 처리하는 쪽 (receiver)의 분리하는 패턴
- 핸들러 체인을 사용해서 요청을 처리한다.

## 적용 전
Request
```java
package com.jikim.designpatterns._03_behavioral_patterns._13_chain_of_responsibilities.before;

public class Request {

	private String body;

	public Request(String body) {
		this.body = body;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
```
RequestHandler
```java
package com.jikim.designpatterns._03_behavioral_patterns._13_chain_of_responsibilities.before;

public class RequestHandler {

	public void handler(Request request) {
		System.out.println(request.getBody());
	}
}
```
LoggingRequestHandler
```java
package com.jikim.designpatterns._03_behavioral_patterns._13_chain_of_responsibilities.before;

public class LoggingRequestHandler extends RequestHandler {

	@Override
	public void handler(Request request) {
		System.out.println("로깅");
		super.handler(request);
	}
}
```
AuthRequestHandler
```java
package com.jikim.designpatterns._03_behavioral_patterns._13_chain_of_responsibilities.before;

public class AuthRequestHandler extends RequestHandler {

	public void handler(Request request) {
		System.out.println("인증이 되었나?");
		System.out.println("이 핸들러를 사용할 수 있는 유저인가?");
		super.handler(request);
	}
}
```
Client
```java
package com.jikim.designpatterns._03_behavioral_patterns._13_chain_of_responsibilities.before;

public class Client {

	public static void main(String[] args) {
		Request request = new Request("무궁화 꽃이 피었습니다.");
		// RequestHandler requestHandler = new RequestHandler();
		// RequestHandler requestHandler = new AuthRequestHandler();
		RequestHandler requestHandler = new LoggingRequestHandler();
		requestHandler.handler(request);
	}
}
```
- Client에서 직접 RequestHandler, AuthRequestHandler, LoggingRequestHandler 골라주어야 한다.

## 적용 후
Request
```java
package com.jikim.designpatterns._03_behavioral_patterns._13_chain_of_responsibilities.after;

public class Request {

	private String body;

	public Request(String body) {
		this.body = body;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
```
RequestHandler - abstract class
```java
package com.jikim.designpatterns._03_behavioral_patterns._13_chain_of_responsibilities.after;

public abstract class RequestHandler {

	private RequestHandler nextHandler;

	public RequestHandler(RequestHandler nextHandler) {
		this.nextHandler = nextHandler;
	}

	public void handle(Request request) {
		if (nextHandler != null) {
			nextHandler.handle(request);
		}
	}
}
```
AuthRequestHandler
```java
package com.jikim.designpatterns._03_behavioral_patterns._13_chain_of_responsibilities.after;

public class AuthRequestHandler extends RequestHandler {

	public AuthRequestHandler(RequestHandler nextHandler) {
		super(nextHandler);
	}

	@Override
	public void handle(Request request) {
		System.out.println("인증이 되었는가?");
		super.handle(request);
	}
}
```
LoggingRequestHandler
```java
package com.jikim.designpatterns._03_behavioral_patterns._13_chain_of_responsibilities.after;

public class LoggingRequestHandler extends RequestHandler {

	public LoggingRequestHandler(RequestHandler nextHandler) {
		super(nextHandler);
	}

	@Override
	public void handle(Request request) {
		System.out.println("로깅");
		super.handle(request);
	}
}
```
PrintRequestHandler
```java
package com.jikim.designpatterns._03_behavioral_patterns._13_chain_of_responsibilities.after;

public class PrintRequestHandler extends RequestHandler {

	public PrintRequestHandler(RequestHandler nextHandler) {
		super(nextHandler);
	}

	@Override
	public void handle(Request request) {
		System.out.println(request.getBody());
		super.handle(request);
	}
}
```
Client
```java
package com.jikim.designpatterns._03_behavioral_patterns._13_chain_of_responsibilities.after;

public class Client {

	private RequestHandler requestHandler;

	public Client(RequestHandler requestHandler) {
		this.requestHandler = requestHandler;
	}

	public void doWork() {
		Request request = new Request("이번 놀이는 뽑기입니다.");
		requestHandler.handle(request);
	}

	public static void main(String[] args) {
		RequestHandler chain = new AuthRequestHandler(new LoggingRequestHandler(new PrintRequestHandler(null)));
		Client client = new Client(chain);
		client.doWork();
	}
}
```

## 장점과 단점
### 장점
- 클라이언트 코드를 변경하지 않고 새로운 핸들러를 체인에 추가할 수 있다.
  - OCP 원칙
- 각각의 체인은 자신이 해야하는 일만 한다.
  - SRP 원칙
- 체인을 다양한 방법으로 구성할 수 있다.

### 단점
- 디버깅이 조금 어렵다.
