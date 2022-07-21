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
