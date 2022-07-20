# 템플릿 메소드 (Template method) 패턴
알고리듬 구조를 서브 클래스가 확장할 수 있도록 템플릿으로 제공하는 방법
- 추상 클래스는 템플릿을 제공하고 하위 클래스는 구체적인 알고리듬을 제공한다.
- 상속 대신 익명 내부 클래스 또는 람다 표현식을 활용할 수 있다.

## 적용 전
FileProcessor
```java
package com.jikim.designpatterns._03_behavioral_patterns._22_template_method;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor {
	private String path;

	public FileProcessor(String path) {
		this.path = path;
	}

	public int process() {
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			int result = 0;
			String line = null;
			while ((line = reader.readLine()) != null) {
				// Integer.valueOf() : Integer 리턴
				// Integer.parseInt() : int 리턴
				result += Integer.parseInt(line);
			}
			return result;
		} catch (IOException e) {
			throw new IllegalArgumentException(path + "에 해당하는 파일이 없습니다.", e);
		}
	}
}
```
MultiplyFileProcessor
```java
package com.jikim.designpatterns._03_behavioral_patterns._22_template_method;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MultiplyFileProcessor {
	private String path;

	public MultiplyFileProcessor(String path) {
		this.path = path;
	}

	public int process() {
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			int result = 1;
			String line = null;
			while ((line = reader.readLine()) != null) {
				// Integer.valueOf() : Integer 리턴
				// Integer.parseInt() : int 리턴
				result *= Integer.parseInt(line);
			}
			return result;
		} catch (IOException e) {
			throw new IllegalArgumentException(path + "에 해당하는 파일이 없습니다.", e);
		}
	}
}
```
Client
```java
package com.jikim.designpatterns._03_behavioral_patterns._22_template_method;

public class Client {
	public static void main(String[] args) {
		FileProcessor fileProcessor = new FileProcessor("number.txt");
		MultiplyFileProcessor fileProcessor1 = new MultiplyFileProcessor("number.txt");
		int result = fileProcessor.process();
		int multiResult = fileProcessor1.process();
		System.out.println(result);
		System.out.println(multiResult);
	}
}
```
- FileProcessor와 MultiplyFileProcessor 사이에 중복된 코드가 많다.
