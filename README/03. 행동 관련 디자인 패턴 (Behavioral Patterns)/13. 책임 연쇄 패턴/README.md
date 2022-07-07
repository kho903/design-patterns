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
