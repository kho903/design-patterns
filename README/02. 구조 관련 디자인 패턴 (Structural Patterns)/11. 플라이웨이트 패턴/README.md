# 플라이웨이트 패턴
객체를 가볍게 만들어 메모리 사용을 줄이는 패턴
- 자주 변하는 속성 (또는 외적인 속성, extrinsit)과 변하지 않는 속성 (또는 내적인 속성, intrinsit)을 분리하고 재사용하여 메모리
사용을 줄일 수 있다.

## 적용 전
Character
```java
package com.jikim.designpatterns._02_structural_patterns._11_flyweight.before;

public class Character {

	private char value;
	private String color;
	private String fontFamily;
	private int fontSize;

	public Character(char value, String color, String fontFamily, int fontSize) {
		this.value = value;
		this.color = color;
		this.fontFamily = fontFamily;
		this.fontSize = fontSize;
	}
}
```
Client
```java
package com.jikim.designpatterns._02_structural_patterns._11_flyweight.before;

public class Client {
	public static void main(String[] args) {
		Character c1 = new Character('h', "white", "Nanum", 12);
		Character c2 = new Character('e', "white", "Nanum", 12);
		Character c3 = new Character('l', "white", "Nanum", 12);
		Character c4 = new Character('l', "white", "Nanum", 12);
		Character c5 = new Character('o', "white", "Nanum", 12);
	}
}
```
