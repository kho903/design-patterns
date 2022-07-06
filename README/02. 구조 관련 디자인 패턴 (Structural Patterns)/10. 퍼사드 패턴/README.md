# 퍼사드 (Facade) 패턴
복잡한 서브 시스템 의존성을 최소화하는 방법
- 클라이언트가 사용해야 하는 복잡한 서브 시스템 의존성을 간단한 인터페이스로 추상화 할 수 있다.

## 적용 전
Client
```java
package com.jikim.designpatterns._02_structural_patterns._10_facade.before;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Client {

	public static void main(String[] args) {
		String to = "gmldnr2222@naver.com";
		String from = "keesun@whiteship.me";
		String host = "127.0.0.1";

		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);

		Session session = Session.getDefaultInstance(properties);

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Test Mail from Java Program");
			message.setText("message");

			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
```
- mail 라이브러리에 많이 의존하고 있다.
