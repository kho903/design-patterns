# 싱글톤 (Singleton) 패턴
- 인스턴스를 오직 한개만 제공하는 클래스
- 시스템 런타임, 환경 세팅에 대한 정보 등, 인스턴스가 여러 개일때 문제가 생길 수 있는 경우가 있따.
인스턴스를 오직 한 개만 만들어 제공하는 클래스가 필요하다.

## 구현 방법 
### V1 (멀티쓰레드에서 안전 X)
- private 생성자에 static 메소드
```java
public class Settings {
   private static Settings instance;

   private Settings() {
   }

   public static Settings getInstance() {
      if (instance == null) {
         instance = new Settings();
      }
      return instance;
   }
}
```
1. 생성자를 private 으로 만든 이유 ?
   - Settings 타입의 인스턴스를 외부에서 new 로 만들지 못하게 하기 위해
   - 외부에서 생성할 경우 새로운 인스턴스가 계속해서 만들어짐
2. getInstance() 메소드를 static으로 선언한 이유?
   - 외부에서 getInstance를 사용할 수 있게 static으로 선언
3. getInstance() 가 멀티쓰레드 환경에서 안전하지 않은 이유?
   - 여러 쓰레드가 동시에 getInstance를 호출할 경우, new가 여러번 호출될 수 있음

### V2 (V2~ thread safe)
- 동기화 (synchronized)를 사용해 멀티쓰레드 환경에 안전하게 만드는 방법
- 성능에 불이익이 있을 수 있음
```java
public class Settings {

   private static Settings instance;

   private Settings() {
   }

   public static synchronized Settings getInstance() {
      if (instance == null) {
         instance = new Settings();
      }
      return instance;
   }

}
```
1. 자바의 동기화 블럭 처리방법
   - synchronized 키워드를 사용하여 메서드에 한 번에 하나의 쓰레드만 들어올 수 있도록 함
2. getInstance() 메소드 동기화시 사용하는 락(lock)은 인스턴스의 락 or 클래스의 락? 그 이유는?
   - static 메소드는 클래스 레벨의 락이다.
   - https://howtodoinjava.com/java/multi-threading/object-vs-class-level-locking/

### V3
- 이른 초기화 (eager initialization)를 사용하는 방법
```java

public class Settings {

   // 이른 초기화 (eager initialization)
   private static final Settings INSTANCE = new Settings();

   private Settings() {
   }

   public static Settings getInstance() {
      return INSTANCE;
   }

}
```
1. 이른 초기화가 단점이 될 수도 있는 이유?
- 미리 만든다는 것 자체가 단점이 될 수 있다.
   - 인스턴스를 만드는 과정 자체가 복잡할 경우, 미리 만들었는데 사용하지 않는 경우 발생 시 낭비
2. 만약에 생성자에서 checked 예외를 던진다면 이 코드를 어떻게 변경해야 할까?
   - checked exception의 경우 호출하는 쪽에서 try-catch 문으로 감싸준다.
   - 하지만 예제처럼 변수를 초기화 할 때에는 try-catch문을 사용할 수 없는데, 이 때, static{} 블록 내에서
   instance를 초기화한다. (final 사용 불가)

### V4
- double checked locking 으로 효율적인 동기화 블럭 만들기
- instance가 null일 경우에만 synchronized 가 걸리므로 효율적이다
```java
public class Settings {

   private static volatile Settings instance;

   private Settings() {

   }

   public static Settings getInstance() {
      // double-checked locking
      if (instance == null) {
         synchronized (Settings.class) {
            if (instance == null) {
               instance = new Settings();
            }
         }
      }
      return instance;
   }

}
```
1. double check locking 이라고 부르는 이유?
   - synchronized 의 밖과 안에서 두 번 Null 체크를 하기 때문에
2. instance 변수는 어떻게 정의해야 하는가? 그 이유는?
   - volatile 키워드로 정의해야 한다.
   - volatile 키워드를 이용하면 캐시 불일치 이슈를 방지할 수 있다. 실제로 자바 메모리 모델은 부분적으로 초기화 된 객체의 발행을 허용하기 때문에 
    파악하기 어려운 버그를 만들어 낼 수 있다.
   - https://letyarch.blogspot.com/2019/04/singleton-synchronized_8.html
   - volatile 이란 ? 
     - java 변수를 Main Memory에 저장하겠다 라는 것을 명시
     - 매번 변수의 값을 읽을 때마다 CPU cache에 저장된 값이 아닌 Main Memory에서 읽는 것
     - https://junghyungil.tistory.com/99

### V5
- static inner 클래스를 사용하는 방법
```java
public class Settings {

   // static inner 클래스
   private Settings() {
   }

   private static class SettingsHolder {
      private static final Settings INSTANCE = new Settings();
   }

   public static Settings getInstance() {
      return SettingsHolder.INSTANCE;
   }

}
```
1. 이 방법은 static final 을 썼는데도 왜 지연 초기화 (lazy initialization)이라고 볼 수 있는가?
- inner class 내에 static final 로 해당 변수는 getInstance() 메서드가 호출될 때 로딩되기 때문에 지연 초기화로 볼 수 있다.

## 싱글톤 (Singleton) 패턴 구현 깨트리기
### 리플렉션 사용
```java
public class App {
	public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		// 리플렉션 사용하기
		Settings settings = Settings.getInstance();
		Constructor<Settings> constructor = Settings.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		Settings settings1 = constructor.newInstance();

		System.out.println(settings == settings1); // false
	}
}
```
1. 리플렉션이란?
    - 구체적인 클래스 타입을 알지 못해도, 그 클래스의 메소드, 타입, 변수들에 접근할 수 있도록 해주는 자바 API
2. setAccessible(true)를 사용하는 이유는?
    - 예제에서 기본 생성자가 private으로 외부에서 호출할 수 없다.
    - but, setAccessible(true)를 통해 privat으로 만든 기본 생성자에 접근할 수 있다.

### 직렬화 & 역직렬화 사용
```java
public class App {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// 직렬화, 역직렬화
		Settings settings = Settings.getInstance();
		Settings settings1 = null;
		try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream("settings.obj"))) {
			out.writeObject(settings);
		}

		try (ObjectInput in = new ObjectInputStream(new FileInputStream("settings.obj"))) {
			settings1 = (Settings)in.readObject();
		}

		System.out.println(settings == settings1); // false
	}
}
```
1. 자바의 직렬화 & 역직렬화에 대해 설명하시오.
   1. 직렬화 (Serialize)
      - 자바 내의 Object 또는 Data를 외부에서 사용할 수 있도록 byte 형태로 데이터를 변환하는 기술이다.
   2. 역질렬화 (Deserialize)
      - byte 형태로 변환된 Data를 다시 자바 내의 Object 또는 Data 변환하는 기술
2. SerializableId란 무엇이며 왜 쓰는가?
    - Serializable 상속 클래스에서 versioning 용도로 serialVersionUID 사용
    - 파일 등으로 저장을 할 때 해당하는 클래스의 버전이 맞는 지 확인하는 중요한 장치
    - 작성하지 않으면 JVM에서 자동으로 작성을 하고, 버전마다 다르기 때문에 직접 설정하는 것을 권장
3. try-resource 블럭에 대한 설명
    - try-with-resources는 try에 자원 객체를 전달하면, try 코드 블록이 끝나면 자동으로 자원을 종료해주는 기능
    - 따로 finally나 catch가 필요 X

## 안전하고 단순하게 구현하는 방법
### V6
- enum을 사용하는 방법
```java
public enum Settings {
	INSTANCE;
}
```
1. enum 타입의 인스턴스를 리플렉션을 통해 만들 수 있는가?
   - 리플렉션을 통해 만들 수 없도록 막혀있다.
2. enum으로 싱글톤 타입을 구현할 때의 단점은?
   - 미리 인스턴스가 만들어진다. (eager initialization), (자원낭비가 크지 않다면 가장 안전한 방법)
   - 상속이 불가능하다.
3. 직렬화 & 역직렬화 시에 별도로 구현해야 하는 메소드가 있는가?
   - enum 클래스 자체에서 Serializable을 이미 구현하고 있기 때문에 별도로 구현이 필요없다.

## 정리
1. 자바에서 enum을 사용하지 않고 싱글톤 패턴을 구현하는 방법은?
   - 
2. private 생성자와 static 메소드를 사용하는 방법의 단점은?
   - 멀티 쓰레드에 안전하지 않다. (thread-safe X)
3. enum을 사용해 싱글톤 패턴을 구현하는 방법의 장점과 단점은?
   - 장점 : reflection, 직렬화 역질렬화에 안전하다.
   - 단점 : 미리 인스턴스가 만들어지고, 상속이 불가능하다.
4. static 클래스를 사용해 싱글톤 패턴 구현 방법
```java
public class Settings {

   private static class SettingsHolder {
      private static final Settings INSTANCE = new Settings();
   }

   public Settings getInstance() {
      return SettingsHolder.INSTANCE;
   }
}
```

## 실무에서는 어떻게 쓰일까?
- 스프링에서 빈의 스코프 중에 하나로 싱글톤 스코프
```java
public class SpringExample {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
		String hello = applicationContext.getBean("hello", String.class);
		String hello2 = applicationContext.getBean("hello", String.class);
		System.out.println(hello == hello2);
	}
}
```
- 자바 java.lang.Runtime
```java

public class RuntimeExample {
	public static void main(String[] args) {
		Runtime runtime = Runtime.getRuntime();
		System.out.println(runtime.maxMemory());
		System.out.println(runtime.freeMemory());
	}
}
```
- 다른 디자인 패턴 (빌더, 퍼사드, 추상 팩토리 등) 구현체의 일부로 쓰이기도 한다.
