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
