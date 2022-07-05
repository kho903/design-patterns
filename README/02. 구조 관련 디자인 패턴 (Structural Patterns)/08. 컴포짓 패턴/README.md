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
