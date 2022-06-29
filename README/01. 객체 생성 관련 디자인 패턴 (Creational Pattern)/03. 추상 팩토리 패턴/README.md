# 추상 팩토리 (Abstract Factory) 패턴
서로 관련 있는 여러 객체를 만들어주는 인터페이스
- 구체적으로 어떤 클래스의 인스턴스를 (concrete product)를 사용하는지 감출 수 있다.

## 적용 전
`_03_abstract_factory/before` 패키지 내의 코드
```java
public class Ship {

	private String name;

	private String color;

	private String logo;

	private WhiteWheel wheel;

	private WhiteAnchor anchor;
}
```
```java
public class WhiteShipFactory extends DefaultShipFactory {
	@Override
	public Ship createShip() {
		Ship ship = new WhiteShip();
		ship.setAnchor(new WhiteAnchor());
		ship.setWheel(new WhiteWheel());
		return ship;
	}
}
```
- WhiteWheel, WhiteAnchor 구체적인 클래스 사용
