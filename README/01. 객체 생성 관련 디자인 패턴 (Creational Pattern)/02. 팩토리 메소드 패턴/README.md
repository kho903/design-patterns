# 팩토리 메소드 (Factory method) 패턴
구체적으로 어떤 인스턴스를 만들지는 서브 클래스가 정한다.
- 다양한 구현체 (Product)가 있고, 그 중에서 특정한 구현체를 만들 수 있는 다양한 팩토리 
(Creator)를 제공할 수 있다.

## 구현방법
### V1
`_02_factorymethod/before -> _02_factorymethod/after_v1`
- 확장에 열려있고 변경에 닫혀있는 구조로 변경
- but, 아직 클라이언트 코드는 변경에 열려 있음

### V2_1
`_02_factorymethod/after_v2_1`
- Client 코드를 다음과 같이 변경
```java
public class Client {
	public static void main(String[] args) {
		Client client = new Client();
		client.print(new WhiteShipFactory(), "WhiteShip", "keesun@mail.com");
		client.print(new BlackShipFactory(), "blackShip", "keesun@mail.com");
	}

	private void print(ShipFactory shipFactory, String name, String email) {
		System.out.println(shipFactory.orderShip(name, email));
	}

}
```

### V2_2
`_02_factorymethod/after_v2_2`
- DefaultShipFactory를 만들어 sendEmailTo를 따로 구현
- Factory 코드들이 DefaultShipFactory를 상속 받도록 변경
```java
public abstract class DefaultShipFactory implements ShipFactory {

	@Override
	public void sendEmailTo(String email, Ship ship) {
		System.out.println(ship.getName() + "를 완성하였습니다.");
	}
}
```

## 정리
구체적으로 어떤 것을 만들지는 서브 클래스가 정한다.
1. 팩토리 메소드 패턴을 적용했을 때의 장점은? 단점은?
    - 장점 : 객체지향 원칙 중 확장에는 열려 있고, 변경에는 닫혀 있는 OCP 원칙을 적용해 기존 코드를
      건드리지 않고 새로운 인스턴스를 다른 방법으로 확장이 가능하다.
      (Creator와 Product 간의 느슨한 결합)
    - 단점 : 각자의 역할별로 나누기 때문에 클래스의 개수가 늘어난다.
2. "확장에 열려 있고 변경에 닫혀있는 객체 지향 원칙"을 설명하세요.
    - 기존 코드를 변경하지 않고 새로운 기능을 확장할 수 있다는 원칙.
3. 자바 8에 추가된 default 메소드에 대해 설명하세요.
    - 인터페이스에 기본 구현체를 만들 수 있다. 인터페이스를 구현하는 클래스 또는 상속받은 또다른  
      인터페이스에서 해당 메소드를 사용할 수 있다. 자바 8부터는 추상 클래스보다 default 메소드를 
      활용한다.
    - 더 나아가 자바 9부터 interface에 private 메소드를 정의할 수 있다. default나 static
      메서드 내에서만 사용가능
