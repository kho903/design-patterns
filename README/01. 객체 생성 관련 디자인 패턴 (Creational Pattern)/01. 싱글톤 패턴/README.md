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
