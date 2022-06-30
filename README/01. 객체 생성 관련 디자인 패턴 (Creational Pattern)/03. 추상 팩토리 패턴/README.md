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

## 구현 방법
- 확장에 열려 있고 변경에 닫혀 있는 구조
- Anchor, Wheel interface
```java
public interface Anchor {
}
```
```java
public interface Wheel {
}
```
- Ship에서 Anchor와 Wheel 추상클래스에 의존하도록 변경
```java
package com.jikim.designpatterns._01_creational_patterns._03_abstract_factory.after;

public class Ship {
	// ...

	private Wheel wheel;

	private Anchor anchor;
	
	// ...
  
	public void setWheel(Wheel wheel) {
		this.wheel = wheel;
	}

	public void setAnchor(Anchor anchor) {
		this.anchor = anchor;
	}

	// ...
}

``` 
- WhiteWheel, WhiteAnchor (구체 클래스)
```java
public class WhiteWheel implements Wheel {
}
```
```java
public class WhiteAnchor implements Anchor {
}
```
- ShipPartsFactory 생성 (추상 팩토리)
```java
public interface ShipPartsFactory {
	Anchor createAnchor();
	Wheel createWheel();
}
```
- WhiteShipPartsFactory에서 ShipPartsFactory 상속 받아 구현
  - 일련의 규약을 지키는 제품을 만들어 주는 팩토리
```java
public class WhiteShipPartsFactory implements ShipPartsFactory {
	@Override
	public Anchor createAnchor() {
		return new WhiteAnchor();
	}

	@Override
	public Wheel createWheel() {
		return new WhiteWheel();
	}
}
```
- WhiteShipFactory - 클라이언트
```java
public class WhiteShipFactory extends DefaultShipFactory {

	private ShipPartsFactory shipPartsFactory;

	public WhiteShipFactory(ShipPartsFactory shipPartsFactory) {
		this.shipPartsFactory = shipPartsFactory;
	}

	@Override
	public Ship createShip() {
		Ship ship = new WhiteShip();
		ship.setAnchor(shipPartsFactory.createAnchor());
		ship.setWheel(shipPartsFactory.createWheel());
		return ship;
	}
}
```
- 여기서 Pro인 부품을 추가 하려한다.
- WhiteAnchorPro, WhiteWheelPro, WhitePartsProFactory 추가
```java
public class WhiteAnchorPro implements Anchor {
}
```
```java
public class WhiteWheelPro implements Wheel {
}
```
```java
public class WhitePartsProFactory implements ShipPartsFactory {
	@Override
	public Anchor createAnchor() {
		return new WhiteAnchorPro();
	}

	@Override
	public Wheel createWheel() {
		return new WhiteWheelPro();
	}
}
```
- ShipInventory : 실행 코드
```java
public class ShipInventory {
	public static void main(String[] args) {
		ShipFactory shipFactory = new WhiteShipFactory(new WhiteShipPartsFactory());
		Ship ship = shipFactory.createShip();
		System.out.println(ship.getAnchor().getClass());
		System.out.println(ship.getWheel().getClass());
		System.out.println();

		ShipFactory shipProFactory = new WhiteShipFactory(new WhitePartsProFactory());
		Ship shipPro = shipProFactory.createShip();
		System.out.println(shipPro.getAnchor().getClass());
		System.out.println(shipPro.getWheel().getClass());
	}
  /**
   * 출력 결과
   class com.jikim.designpatterns._01_creational_patterns._03_abstract_factory.after_v1.WhiteAnchor
   class com.jikim.designpatterns._01_creational_patterns._03_abstract_factory.after_v1.WhiteWheel

   class com.jikim.designpatterns._01_creational_patterns._03_abstract_factory.after_v1.WhiteAnchorPro
   class com.jikim.designpatterns._01_creational_patterns._03_abstract_factory.after_v1.WhiteWheelPro
   */
}
```

## 정리
팩토리 메소드 패턴과 굉장히 흡사한데 무엇이 다른건가?
- 모양과 효과는 비슷하지만 ...
  - 둘 다 구체적인 객체 생성 과정을 추상화한 인터페이스를 제공한다.
- 관점이 다르다.
  - 팩토리 메소드 패턴은 "팩토리를 구현하는 방법 (inheritance)"에 초점을 둔다.
  - 추상 팩토리 페턴은 "팩토리를 사용하는 방법 (composition)"에 초점을 둔다.
- 목적이 조금 다르다.
  - 팩토리 메소드 패턴은 구체적인 객체 생성 과정을 하위 또는 구체적인 클래스로 옮기는 것이 목적
  - 추상 팩토리 패턴은 관련있는 여러 객체를 구체적인 클래스에 의존하지 않고 만들 수 있게 해주는 것이 목적.
