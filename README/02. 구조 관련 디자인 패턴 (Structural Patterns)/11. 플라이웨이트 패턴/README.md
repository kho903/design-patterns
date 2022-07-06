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

### 적용 후
Font
```java
package com.jikim.designpatterns._02_structural_patterns._11_flyweight.after;

public final class Font {

	final String family;

	final int size;

	public Font(String family, int size) {
		this.family = family;
		this.size = size;
	}

	public String getFamily() {
		return family;
	}

	public int getSize() {
		return size;
	}
}
```
FontFactory
```java
package com.jikim.designpatterns._02_structural_patterns._11_flyweight.after;

import java.util.HashMap;
import java.util.Map;

public class FontFactory {
	private Map<String, Font> cache = new HashMap<>();

	public Font getFont(String font) {
		if (cache.containsKey(font)) {
			return cache.get(font);
		} else {
			String[] split = font.split(":");
			Font newFont = new Font(split[0], Integer.parseInt(split[1]));
			cache.put(font, newFont);
			return newFont;
		}
	}
}
```
Character
```java
package com.jikim.designpatterns._02_structural_patterns._11_flyweight.after;

public class Character {

	private char value;

	private String color;

	private Font font;

	public Character(char value, String color, Font font) {
		this.value = value;
		this.color = color;
		this.font = font;
	}
}
```
Client
```java
package com.jikim.designpatterns._02_structural_patterns._11_flyweight.after;

public class Client {
	public static void main(String[] args) {
		FontFactory fontFactory = new FontFactory();
		Character c1 = new Character('h', "white", fontFactory.getFont("nanum:12"));
		Character c2 = new Character('e', "white", fontFactory.getFont("nanum:12"));
		Character c3 = new Character('l', "white", fontFactory.getFont("nanum:12"));
		Character c4 = new Character('l', "white", fontFactory.getFont("nanum:12"));
		Character c5 = new Character('o', "white", fontFactory.getFont("nanum:12"));
	}
}
```

## 장점과 단점
### 장점
- 애플리케이션에서 사용하는 메모리를 줄일 수 있다.

### 단점
- 코드의 복잡도가 증가한다.

## 자바에서 찾아보는 패턴
- Integer.valueOf(int)
- 캐시를 제공한다.
- https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html#valueOf-int-
