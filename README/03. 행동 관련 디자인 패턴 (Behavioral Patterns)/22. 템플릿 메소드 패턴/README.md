# 템플릿 메소드 (Template method) 패턴
알고리듬 구조를 서브 클래스가 확장할 수 있도록 템플릿으로 제공하는 방법
- 추상 클래스는 템플릿을 제공하고 하위 클래스는 구체적인 알고리듬을 제공한다.

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

## 적용 후
FileProcessor
```java
package com.jikim.designpatterns._03_behavioral_patterns._22_template_method.after;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class FileProcessor {
	private String path;

	public FileProcessor(String path) {
		this.path = path;
	}

	public int process() {
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			int result = 0;
			String line = null;
			while ((line = reader.readLine()) != null) {
				result = getResult(result, Integer.parseInt(line));
			}
			return result;
		} catch (IOException e) {
			throw new IllegalArgumentException(path + "에 해당하는 파일이 없습니다.", e);
		}
	}

	protected abstract int getResult(int result, int number);
}
```
Plus
```java
package com.jikim.designpatterns._03_behavioral_patterns._22_template_method.after;

public class Plus extends FileProcessor {

	public Plus(String path) {
		super(path);
	}

	@Override
	protected int getResult(int result, int number) {
		return result += number;
	}
}
```
Multiply
```java
package com.jikim.designpatterns._03_behavioral_patterns._22_template_method.after;

public class Multiply extends FileProcessor {

	public Multiply(String path) {
		super(path);
	}

	@Override
	protected int getResult(int result, int number) {
		if (result == 0) result = 1;
		return result *= number;
	}
}
```
Client
```java
package com.jikim.designpatterns._03_behavioral_patterns._22_template_method.after;

public class Client {
	public static void main(String[] args) {
		FileProcessor fileProcessor = new Plus("number.txt");
		FileProcessor fileProcessor1 = new Multiply("number.txt");
		int result = fileProcessor.process();
		int resultMulti = fileProcessor1.process();
		System.out.println(result);
		System.out.println(resultMulti);
	}
}
```

# 템플릿 콜백 (Template-Callback) 패턴
- 상속 대신 익명 내부 클래스 또는 람다 표현식을 활용할 수 있다.

FileProcessor
```java
package com.jikim.designpatterns._03_behavioral_patterns._22_template_method.template_callback;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor {
	private String path;

	public FileProcessor(String path) {
		this.path = path;
	}

	public int process(Operator operator) {
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			int result = 0;
			String line = null;
			while ((line = reader.readLine()) != null) {
				result = operator.getResult(result, Integer.parseInt(line));
			}
			return result;
		} catch (IOException e) {
			throw new IllegalArgumentException(path + "에 해당하는 파일이 없습니다.", e);
		}
	}
}
```
Operator
```java
package com.jikim.designpatterns._03_behavioral_patterns._22_template_method.template_callback;

public interface Operator {
	abstract int getResult(int result, int number);
}
```
Client
```java
package com.jikim.designpatterns._03_behavioral_patterns._22_template_method.template_callback;

public class Client {
	public static void main(String[] args) {
		FileProcessor fileProcessor = new FileProcessor("number.txt");
		int result = fileProcessor.process((result1, number) -> result1 += number);
		int result2 = fileProcessor.process(new Plus());
		System.out.println(result);
		System.out.println(result2);
	}
}
```
implement로 구현또한 가능
```java
package com.jikim.designpatterns._03_behavioral_patterns._22_template_method.template_callback;

public class Plus implements Operator {
	@Override
	public int getResult(int result, int number) {
		return result += number;
	}
}
```

## 템플릿 메소드 패턴 장점과 단점
### 장점
- 템플릿 코드를 재사용하고 중복 코드를 줄일 수 있다.
- 템플릿 코드를 변경하지 않고 상속을 받아서 구체적인 알고리듬만 변경할 수 있다.

### 단점
- 리스코프 치환 원칙을 위반할 수도 있다. -> final 키워드로 어느정도는 막을 수 있다.
  - 리스코프 치환 원칙 : 상속 구조에서 상위 클래스 타입으로 사용하는 코드에서 그 코드를 하위 타입으로 
  치환해도 정상적으로 동작해야 한다.
- 알고리듬 구조가 복잡할 수록 템플릿을 유지하기 어려워진다.
