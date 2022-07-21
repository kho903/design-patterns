# 방문자 (Visitor) 패턴
기존 코드를 변경하지 않고 새로운 기능을 추가하는 방법
- 더블 디스패치 (Double Dispatch)를 활용할 수 있다.

## 적용 전
Device
```java
package com.jikim.designpatterns._03_behavioral_patterns._23_visitor.before;

public interface Device {
}
```
Phone
```java
package com.jikim.designpatterns._03_behavioral_patterns._23_visitor.before;

public class Phone implements Device {
}
```
Watch
```java
package com.jikim.designpatterns._03_behavioral_patterns._23_visitor.before;

public class Watch implements Device {
}
```
Shape
```java
package com.jikim.designpatterns._03_behavioral_patterns._23_visitor.before;

public interface Shape {

	void printTo(Device device);
}
```
Circle
```java
package com.jikim.designpatterns._03_behavioral_patterns._23_visitor.before;

public class Circle implements Shape {
	@Override
	public void printTo(Device device) {
		if (device instanceof Phone) {
			System.out.println("print Circle to phone");
		} else if (device instanceof Watch) {
			System.out.println("print Circle to watch");
		}
	}
}
```
Triangle
```java
package com.jikim.designpatterns._03_behavioral_patterns._23_visitor.before;

public class Triangle implements Shape {
	@Override
	public void printTo(Device device) {
		if (device instanceof Phone) {
			System.out.println("print Triangle to phone");
		} else if (device instanceof Watch) {
			System.out.println("print Triangle to watch");
		}
	}
}
```
Rectangle
```java
package com.jikim.designpatterns._03_behavioral_patterns._23_visitor.before;

public class Rectangle implements Shape {
	@Override
	public void printTo(Device device) {
		if (device instanceof Phone) {
			System.out.println("print Rectangle to phone");
		} else if (device instanceof Watch) {
			System.out.println("print Rectangle to watch");
		}
	}
}
```
Client
```java
package com.jikim.designpatterns._03_behavioral_patterns._23_visitor.before;

public class Client {
	public static void main(String[] args) {
		Shape rectangle = new Rectangle();
		rectangle.printTo(new Phone());
		rectangle.printTo(new Watch());
		Shape triangle = new Triangle();
		triangle.printTo(new Phone());
	}
}
```

## 적용 후
Device
```java
package com.jikim.designpatterns._03_behavioral_patterns._23_visitor.after;

public interface Device {
	void print(Circle circle);

	void print(Triangle triangle);

	void print(Rectangle rectangle);

}
```
Phone
```java
package com.jikim.designpatterns._03_behavioral_patterns._23_visitor.after;

public class Phone implements Device {
	@Override
	public void print(Circle circle) {
		System.out.println("Print Circle to Phone");
	}

	@Override
	public void print(Triangle triangle) {
		System.out.println("Print Triangle to Phone");
	}

	@Override
	public void print(Rectangle rectangle) {
		System.out.println("Print Rectangle to Phone");
	}
}
```
Watch
```java
package com.jikim.designpatterns._03_behavioral_patterns._23_visitor.after;

public class Watch implements Device {
	@Override
	public void print(Circle circle) {
		System.out.println("Print Circle to Watch");
	}

	@Override
	public void print(Triangle triangle) {
		System.out.println("Print Triangle to Watch");
	}

	@Override
	public void print(Rectangle rectangle) {
		System.out.println("Print Rectangle to Watch");
	}
}
```
Pad
```java
package com.jikim.designpatterns._03_behavioral_patterns._23_visitor.after;

public class Pad implements Device {

	@Override
	public void print(Circle circle) {
		System.out.println("Print Circle to Pad");
	}

	@Override
	public void print(Triangle triangle) {
		System.out.println("Print Triangle to Pad");
	}

	@Override
	public void print(Rectangle rectangle) {
		System.out.println("Print Rectangle to Pad");
	}
}
```
Shape
```java
package com.jikim.designpatterns._03_behavioral_patterns._23_visitor.after;

public interface Shape {

	void accept(Device device);
}
```
Circle
```java
package com.jikim.designpatterns._03_behavioral_patterns._23_visitor.after;

public class Circle implements Shape {

	@Override
	public void accept(Device device) {
		device.print(this);
	}
}
```
Rectangle
```java
package com.jikim.designpatterns._03_behavioral_patterns._23_visitor.after;

public class Rectangle implements Shape {
	@Override
	public void accept(Device device) {
		device.print(this);
	}
}
```
Triangle
```java
package com.jikim.designpatterns._03_behavioral_patterns._23_visitor.after;

public class Triangle implements Shape {
	@Override
	public void accept(Device device) {
		device.print(this);
	}
}
```
Client
```java
package com.jikim.designpatterns._03_behavioral_patterns._23_visitor.after;

public class Client {
	public static void main(String[] args) {
		Shape rectangle = new Rectangle();
		// Device device = new Phone();
		Device device = new Pad();
		rectangle.accept(device);
	}
}
```

## 장점과 단점
### 장점
- 기존 코드를 변경하지 않고 새로운 코드를 추가할 수 있다.
- 추가 기능을 한 곳에 모아둘 수 있다.

### 단점
- 복잡하다.
- 새로운 Element를 추가하거나 제거할 때 모든 Visitor 코드를 변경해야 한다.
