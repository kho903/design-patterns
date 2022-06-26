# 싱글톤 (Singleton) 패턴
- 인스턴스를 오직 한개만 제공하는 클래스
- 시스템 런타임, 환경 세팅에 대한 정보 등, 인스턴스가 여러 개일때 문제가 생길 수 있는 경우가 있따.
인스턴스를 오직 한 개만 만들어 제공하는 클래스가 필요하다.

## 구현 방법 
### V1
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