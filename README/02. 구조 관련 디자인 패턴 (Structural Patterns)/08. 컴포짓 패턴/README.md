# 컴포짓 (Composite) 패턴
그룹 전체와 개별 객체를 동일하게 처리할 수 있는 패턴
- 클라이언트 입장에서는 '전체'나 '부분'이나 모두 동일한 컴포넌트로 인식할 수 있는 
계층 구조를 만든다. (Part-Whole Hierarchy, 부분-전체 계층)

## 적용 전
Item
```java
package com.jikim.designpatterns._02_structural_patterns._08_composite.before;

public class Item {
	private String name;
	private int price;

	public Item(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public int getPrice() {
		return price;
	}
}

```
Bag
```java
package com.jikim.designpatterns._02_structural_patterns._08_composite.before;

import java.util.ArrayList;
import java.util.List;

public class Bag {

	private List<Item> items = new ArrayList<>();

	public void add(Item item) {
		items.add(item);
	}

	public List<Item> getItems() {
		return items;
	}
}
```
Client
```java
package com.jikim.designpatterns._02_structural_patterns._08_composite.before;

public class Client {
	public static void main(String[] args) {
		Item doranBlade = new Item("도란검", 450);
		Item healPotion = new Item("체력 물약", 50);

		Bag bag = new Bag();
		bag.add(doranBlade);
		bag.add(healPotion);

		Client client = new Client();
		client.printPrice(doranBlade);
		client.printPrice(bag);
	}
	private void printPrice(Item item) {
		System.out.println(item.getPrice());
	}

	private void printPrice(Bag bag) {
		int sum = bag.getItems().stream().mapToInt(Item::getPrice).sum();
		System.out.println(sum);
	}

}
```

## 적용 후
Component 인터페이스
```java
package com.jikim.designpatterns._02_structural_patterns._08_composite.after;

public interface Component {

	int getPrice();
}
```
Item
```java
package com.jikim.designpatterns._02_structural_patterns._08_composite.after;

public class Item implements Component {
	private String name;
	private int price;

	public Item(String name, int price) {
		this.name = name;
		this.price = price;
	}

	@Override
	public int getPrice() {
		return this.price;
	}
}
```
Bag
```java
package com.jikim.designpatterns._02_structural_patterns._08_composite.after;

import java.util.ArrayList;
import java.util.List;

public class Bag implements Component {

	private List<Component> components = new ArrayList<>();

	public void add(Component component) {
		components.add(component);
	}

	public List<Component> getComponents() {
		return components;
	}

	@Override
	public int getPrice() {
		return components.stream().mapToInt(Component::getPrice).sum();
	}
}
```
Client
```java
package com.jikim.designpatterns._02_structural_patterns._08_composite.after;

public class Client {

	public static void main(String[] args) {
		Item doranBlade = new Item("도란검", 450);
		Item healPotion = new Item("체력 물약", 50);

		Bag bag = new Bag();
		bag.add(doranBlade);
		bag.add(healPotion);

		Client client = new Client();
		client.printPrice(doranBlade);
		client.printPrice(bag);

	}

	private void printPrice(Component component) {
		System.out.println(component.getPrice());
	}
}
```
- 클라이언트 코드에서 값을 계산하는 로직을 몰라도 된다.

## 장점과 단점
### 장점
- 복잡한 트리 구조를 편리하게 사용할 수 있다.
- 다형성과 재귀를 활용할 수 있다.
- 클라이언트 코드를 변경하지 않고, 새로운 엘리먼트 타입을 추가할 수 있다.

### 단점
- 트리를 만들어야 하기 때문에 (공통된 인터페이스를 정의해야 하기 때문에) 지나치게 일반화해야 하는
경우도 생길 수 있다.

## 실무에서 어떻게 쓰일까?
### 자바
- Swing 라이브러리
- JSF (JavaServer Faces) 라이브러리
